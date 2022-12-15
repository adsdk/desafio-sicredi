package br.com.desafiosicredi.domain;

import br.com.desafiosicredi.domain.enums.VotoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Voto {

    private String documento;
    private VotoEnum vote;

}
