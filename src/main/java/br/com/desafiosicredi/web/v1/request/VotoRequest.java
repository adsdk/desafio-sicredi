package br.com.desafiosicredi.web.v1.request;

import br.com.desafiosicredi.domain.enums.VotoEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VotoRequest {

    @NotBlank
    private String documento;

    @NotBlank
    private VotoEnum voto;
}
