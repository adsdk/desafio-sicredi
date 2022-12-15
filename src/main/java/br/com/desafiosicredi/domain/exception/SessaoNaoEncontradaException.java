package br.com.desafiosicredi.domain.exception;

public class SessaoNaoEncontradaException extends RuntimeException {

    public SessaoNaoEncontradaException(final String message) {
        super(String.format("Sessao %s nao encontrada!", message));
    }

}
