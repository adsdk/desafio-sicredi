package br.com.desafiosicredi.web.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private String errorMessage;

}
