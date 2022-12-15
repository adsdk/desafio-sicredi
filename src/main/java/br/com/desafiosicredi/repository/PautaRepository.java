package br.com.desafiosicredi.repository;

import br.com.desafiosicredi.domain.Pauta;
import br.com.desafiosicredi.domain.exception.PautaNaoEncontradaException;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PautaRepository extends ReactiveCrudRepository<Pauta, String> {

    default Mono<Pauta> obterPautaPorId(String pautaId) {
        return this.findById(pautaId)
                .switchIfEmpty(Mono.error(new PautaNaoEncontradaException(pautaId)));
    }

}
