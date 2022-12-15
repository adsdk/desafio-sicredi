package br.com.desafiosicredi.domain.mapper;

import br.com.desafiosicredi.web.v1.request.SessaoRequest;
import br.com.desafiosicredi.domain.Sessao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessaoMapper {

    public static Sessao toSessao(SessaoRequest sessaoRequest) {
        var now = LocalDateTime.now();
        return Sessao.builder()
                .pautaId(sessaoRequest.getPautaId())
                .sessaoInicio(now)
                .sessaoFim(now.plusMinutes(sessaoRequest.getTempoLimiteVotacao()))
                .votos(new ArrayList<>())
                .build();
    }

}
