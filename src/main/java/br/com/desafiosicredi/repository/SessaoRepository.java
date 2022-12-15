package br.com.desafiosicredi.repository;

import br.com.desafiosicredi.domain.Sessao;
import br.com.desafiosicredi.domain.exception.SessaoNaoEncontradaException;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public interface SessaoRepository extends ReactiveCrudRepository<Sessao, String> {

    default Mono<Sessao> obterSessaoVigentePorId(String sessionId) {
        return this.findById(sessionId)
                .filter(session -> session.getSessaoFim().isAfter(now()))
                .switchIfEmpty(Mono.error(new SessaoNaoEncontradaException(sessionId)));
    }

    Flux<Sessao> findByPautaId(String agendaId);

    Flux<Sessao> findByConsolidadoFalseAndSessaoFimLessThan(LocalDateTime dt);

}
