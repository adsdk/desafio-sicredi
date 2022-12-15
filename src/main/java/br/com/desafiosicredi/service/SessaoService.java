package br.com.desafiosicredi.service;

import br.com.desafiosicredi.domain.exception.SessaoNaoEncontradaException;
import br.com.desafiosicredi.web.v1.request.SessaoRequest;
import br.com.desafiosicredi.web.v1.request.VotoRequest;
import br.com.desafiosicredi.domain.Sessao;
import br.com.desafiosicredi.domain.Voto;
import br.com.desafiosicredi.domain.mapper.ConsolidacaoMapper;
import br.com.desafiosicredi.domain.mapper.SessaoMapper;
import br.com.desafiosicredi.domain.exception.SessaoAbertaException;
import br.com.desafiosicredi.repository.PautaRepository;
import br.com.desafiosicredi.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessaoService {

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    private final QueueSender queueSender;

    public Mono<Sessao> criarSessao(SessaoRequest sessaoRequest) {
        return pautaRepository.obterPautaPorId(sessaoRequest.getPautaId())
                .flatMap(agenda -> sessaoRepository.findByPautaId(sessaoRequest.getPautaId()).collectList())
                .flatMap(session -> Mono.justOrEmpty(session.stream().filter(s -> s.getSessaoFim().isAfter(now())).findFirst()))
                .flatMap(session -> Mono.error(new SessaoAbertaException(session.getPautaId(), session.getId())))
                .switchIfEmpty(Mono.defer(() -> sessaoRepository.save(SessaoMapper.toSessao(sessaoRequest))))
                .cast(Sessao.class);
    }

    public Mono<Sessao> votarSessao(String id, VotoRequest votoRequest) {
        return sessaoRepository.obterSessaoVigentePorId(id)
                .map(session -> {
                    session.getVotos().stream()
                            .filter(voto -> voto.getDocumento().equals(votoRequest.getDocumento()))
                            .findFirst()
                            .ifPresentOrElse(voto -> voto.setVote(votoRequest.getVoto()),
                                    () -> session.getVotos().add(Voto.builder()
                                            .vote(votoRequest.getVoto())
                                            .documento(votoRequest.getDocumento()).build()));

                    return session;
                })
                .flatMap(sessaoRepository::save);

    }

    public Mono<Sessao> obterSessao(String id) {
        return sessaoRepository.findById(id)
                .switchIfEmpty(Mono.error(new SessaoNaoEncontradaException(id)));
    }

    @Scheduled(fixedDelay = 30000)
    public void consolidarSessao() {
        log.info("m=consolidarSessao rodando");
        sessaoRepository.findByConsolidadoFalseAndSessaoFimLessThan(LocalDateTime.now())
                .flatMap(sessao -> pautaRepository.findById(sessao.getPautaId())
                        .flatMap(pauta -> {
                    queueSender.sendConsolidacao(ConsolidacaoMapper.toConsolidacao(pauta, sessao));
                    sessao.setConsolidado(true);
                    return sessaoRepository.save(sessao)
                            .doOnNext(s -> log.info("sessão {} consolidada", s.getId()));
                }))
                .subscribeOn(Schedulers.immediate())
                .subscribe();
    }

}
