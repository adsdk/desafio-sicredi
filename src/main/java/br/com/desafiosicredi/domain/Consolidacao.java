package br.com.desafiosicredi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Consolidacao {

    private String pautaId;
    private String sessaoId;
    private String assuntoPauta;
    private String descricaoPauta;
    private Long votosSim;
    private Long votosNao;

}
