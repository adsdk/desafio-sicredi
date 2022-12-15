package br.com.desafiosicredi.web.v1;

import br.com.desafiosicredi.web.v1.api.PautaApi;
import br.com.desafiosicredi.web.v1.request.PautaRequest;
import br.com.desafiosicredi.domain.Pauta;
import br.com.desafiosicredi.service.PautaService;
import br.com.desafiosicredi.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pauta")
public class PautaController implements PautaApi {

    private final PautaService pautaService;
    private final JsonUtils jsonUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pauta> criarPauta(@Valid @RequestBody final PautaRequest pautaRequest) {
        return pautaService.criarPauta(pautaRequest)
                .doOnNext(a -> log.debug("Nova agenda criada - {}", jsonUtils.toJson(a)));
    }

    @GetMapping
    public Flux<Pauta> listarPautas() {
        return pautaService.listarPautas();
    }

    @GetMapping("/{pautaId}")
    public Mono<Pauta> obterPauta(@PathVariable String pautaId) {
        return pautaService.obterPauta(pautaId);
    }

    @PutMapping("/{pautaId}")
    public Mono<Pauta> editarPauta(@PathVariable String pautaId,
                                   @Valid @RequestBody PautaRequest pautaRequest) {
        return pautaService.editarPauta(pautaId, pautaRequest);
    }

}
