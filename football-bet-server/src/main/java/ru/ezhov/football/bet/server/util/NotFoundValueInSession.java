package ru.ezhov.football.bet.server.util;

public class NotFoundValueInSession extends Throwable {

    public NotFoundValueInSession() {
    }

    public NotFoundValueInSession(String message) {
        super(message);
    }

    public NotFoundValueInSession(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundValueInSession(Throwable cause) {
        super(cause);
    }

    public NotFoundValueInSession(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
