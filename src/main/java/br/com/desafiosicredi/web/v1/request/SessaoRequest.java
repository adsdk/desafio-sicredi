package br.com.desafiosicredi.web.v1.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class SessaoRequest {

    @NotBlank
    private String pautaId;

    @Min(1)
    private Integer tempoLimiteVotacao = 1;

}
