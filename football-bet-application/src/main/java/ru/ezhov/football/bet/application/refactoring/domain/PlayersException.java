package ru.ezhov.football.bet.application.refactoring.domain;

public class PlayersException extends Exception {
    public PlayersException() {
    }

    public PlayersException(String message) {
        super(message);
    }

    public PlayersException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayersException(Throwable cause) {
        super(cause);
    }

    public PlayersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
