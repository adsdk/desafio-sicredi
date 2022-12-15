package br.com.desafiosicredi.domain;

import br.com.desafiosicredi.domain.enums.VotoEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document
public class Sessao {

    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String pautaId;

    @NotBlank
    private LocalDateTime sessaoInicio;

    @NotBlank
    private LocalDateTime sessaoFim;

    private List<Voto> votos;

    private boolean consolidado;

    public Long getVotosSim() {
        return votos.stream().filter(voto -> VotoEnum.SIM.equals(voto.getVote())).count();
    }

    public Long getVotosNao() {
        return votos.stream().filter(voto -> VotoEnum.NAO.equals(voto.getVote())).count();
    }

}
