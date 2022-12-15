package br.com.desafiosicredi.service;

import br.com.desafiosicredi.web.v1.request.PautaRequest;
import br.com.desafiosicredi.domain.Pauta;
import br.com.desafiosicredi.domain.mapper.PautaMapper;
import br.com.desafiosicredi.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    public Mono<Pauta> criarPauta(PautaRequest pautaRequest) {
        return pautaRepository.save(PautaMapper.toPauta(pautaRequest));
    }

    public Flux<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    public Mono<Pauta> obterPauta(String pautaId) {
        return pautaRepository.findById(pautaId);
    }

    public Mono<Pauta> editarPauta(String pautaId, PautaRequest pautaRequest) {
        return pautaRepository.obterPautaPorId(pautaId)
                .flatMap(pauta -> {
                    pauta.setAssunto(pautaRequest.getAssunto());
                    pauta.setDescricao(pautaRequest.getDescricao());
                    return pautaRepository.save(pauta);
                });
    }

}
