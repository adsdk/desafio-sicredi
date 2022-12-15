package br.com.desafiosicredi.domain.mapper;

import br.com.desafiosicredi.domain.Consolidacao;
import br.com.desafiosicredi.domain.Pauta;
import br.com.desafiosicredi.domain.Sessao;

public class ConsolidacaoMapper {

    public static Consolidacao toConsolidacao(Pauta pauta, Sessao sessao) {
        return Consolidacao.builder()
                .pautaId(pauta.getId())
                .sessaoId(sessao.getId())
                .assuntoPauta(pauta.getAssunto())
                .descricaoPauta(pauta.getDescricao())
                .votosSim(sessao.getVotosSim())
                .votosNao(sessao.getVotosNao())
                .build();
    }

}
