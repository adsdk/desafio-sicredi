package br.com.desafiosicredi.web.v1;

import br.com.desafiosicredi.web.v1.api.SessaoApi;
import br.com.desafiosicredi.web.v1.request.SessaoRequest;
import br.com.desafiosicredi.web.v1.request.VotoRequest;
import br.com.desafiosicredi.domain.Sessao;
import br.com.desafiosicredi.service.SessaoService;
import br.com.desafiosicredi.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sessao")
public class SessaoController implements SessaoApi {

    private final SessaoService sessaoService;
    private final JsonUtils jsonUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Sessao> criarSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {
        return sessaoService.criarSessao(sessaoRequest)
                .doOnNext(s -> log.debug("Nova sessão criada - {}", jsonUtils.toJson(s)));
    }

    @PostMapping("/voto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Sessao> votarSessao(@PathVariable final String id,
                                    @Valid @RequestBody VotoRequest votoRequest) {
        return sessaoService.votarSessao(id, votoRequest);
    }

    @GetMapping("/{id}")
    public Mono<Sessao> obterSessao(@PathVariable final String id) {
        return sessaoService.obterSessao(id);
    }

}
