package br.com.desafiosicredi.domain.exception;

public class PautaNaoEncontradaException extends RuntimeException {

    public PautaNaoEncontradaException(final String message) {
        super(String.format("Pauta %s nao encontrada!", message));
    }

}
