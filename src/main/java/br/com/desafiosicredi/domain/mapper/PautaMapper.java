package br.com.desafiosicredi.domain.mapper;

import br.com.desafiosicredi.web.v1.request.PautaRequest;
import br.com.desafiosicredi.domain.Pauta;

public class PautaMapper {

    public static Pauta toPauta(PautaRequest pautaRequest) {
        return Pauta.builder()
                .assunto(pautaRequest.getAssunto())
                .descricao(pautaRequest.getDescricao())
                .build();
    }

}
