package ru.ezhov.football.bet.application.refactoring.infrastructure;

public class AdminLoadException extends Exception {
    public AdminLoadException() {
    }

    public AdminLoadException(String message) {
        super(message);
    }

    public AdminLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminLoadException(Throwable cause) {
        super(cause);
    }

    public AdminLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
