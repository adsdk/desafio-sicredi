package br.com.desafiosicredi.domain.exception;

public class SessaoAbertaException extends RuntimeException {

    public SessaoAbertaException(final String agendaId, final String sessionId) {
        super(String.format("Pauta %s ja encontrasse com a sessao %s aberta!", agendaId, sessionId));
    }

}
