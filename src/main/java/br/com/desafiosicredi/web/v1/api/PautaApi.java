package br.com.desafiosicredi.web.v1.api;

import br.com.desafiosicredi.web.v1.request.PautaRequest;
import br.com.desafiosicredi.domain.Pauta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "agenda", description = "API de pautas")
public interface PautaApi {

    @Operation(
            summary = "Salvar pauta",
            description = "Salva a pauta na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta persistida com sucesso")
    })
    Mono<Pauta> criarPauta(PautaRequest pautaRequest);

    @Operation(
            summary = "Listar pautas",
            description = "Retorna a lista de pautas persistidas na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso")
    })
    Flux<Pauta> listarPautas();

    @Operation(
            summary = "Obter pauta por id",
            description = "Retorna a pauta persistida na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta obtida com sucesso")
    })
    Mono<Pauta> obterPauta(String agendaId);

    @Operation(
            summary = "Atualizar pauta",
            description = "Atualiza a pauta persistida na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta atualizada com sucesso")
    })
    Mono<Pauta> editarPauta(String agendaId, PautaRequest pautaRequest);

}
