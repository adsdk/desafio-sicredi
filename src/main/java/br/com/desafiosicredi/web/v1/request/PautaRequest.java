package br.com.desafiosicredi.web.v1.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PautaRequest {

    @NotBlank
    private String assunto;

    @NotBlank
    private String descricao;

}
