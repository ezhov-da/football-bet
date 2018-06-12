package ru.ezhov.football.bet.application.refactoring.infrastructure;

public class AdminServiceNotLoadException extends RuntimeException {
    public AdminServiceNotLoadException() {
    }

    public AdminServiceNotLoadException(String message) {
        super(message);
    }

    public AdminServiceNotLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminServiceNotLoadException(Throwable cause) {
        super(cause);
    }

    public AdminServiceNotLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
