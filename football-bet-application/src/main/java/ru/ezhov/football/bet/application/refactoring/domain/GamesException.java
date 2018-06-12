package ru.ezhov.football.bet.application.refactoring.domain;

public class GamesException extends Exception {
    public GamesException() {
    }

    public GamesException(String message) {
        super(message);
    }

    public GamesException(String message, Throwable cause) {
        super(message, cause);
    }

    public GamesException(Throwable cause) {
        super(cause);
    }

    public GamesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
