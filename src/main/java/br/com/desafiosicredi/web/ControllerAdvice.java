package br.com.desafiosicredi.web;

import br.com.desafiosicredi.web.v1.response.ErrorResponse;
import br.com.desafiosicredi.domain.exception.PautaNaoEncontradaException;
import br.com.desafiosicredi.domain.exception.SessaoAbertaException;
import br.com.desafiosicredi.domain.exception.SessaoNaoEncontradaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SessaoAbertaException.class)
    public final ResponseEntity<ErrorResponse> handleOpenSessionException(SessaoAbertaException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Já existe uma sessão para esta pauta!")
                        .build());
    }

    @ExceptionHandler(PautaNaoEncontradaException.class)
    public final ResponseEntity<ErrorResponse> handleAgendaNotFoundException(PautaNaoEncontradaException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Pauta não encontrada!")
                        .build());
    }

    @ExceptionHandler(SessaoNaoEncontradaException.class)
    public final ResponseEntity<ErrorResponse> handleSessionNotFoundException(SessaoNaoEncontradaException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Sessão não encontrada!")
                        .build());
    }

}
