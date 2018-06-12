package ru.ezhov.football.bet.application.refactoring.domain;

public class ScoreException extends Exception {
    public ScoreException() {
    }

    public ScoreException(String message) {
        super(message);
    }

    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoreException(Throwable cause) {
        super(cause);
    }

    public ScoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
