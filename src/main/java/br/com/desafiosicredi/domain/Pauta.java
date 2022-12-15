package br.com.desafiosicredi.domain;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Document
public class Pauta {

    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String assunto;

    @NotBlank
    private String descricao;

}
