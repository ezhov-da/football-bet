package ru.ezhov.football.bet.application.refactoring.domain;

public class PlayerAuthorizationException extends Exception {
    public PlayerAuthorizationException() {
    }

    public PlayerAuthorizationException(String message) {
        super(message);
    }

    public PlayerAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAuthorizationException(Throwable cause) {
        super(cause);
    }

    public PlayerAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
