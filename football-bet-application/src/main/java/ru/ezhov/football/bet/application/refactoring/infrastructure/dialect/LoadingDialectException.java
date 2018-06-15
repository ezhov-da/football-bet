package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

public class LoadingDialectException extends Throwable {
    public LoadingDialectException() {
    }

    public LoadingDialectException(String message) {
        super(message);
    }

    public LoadingDialectException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingDialectException(Throwable cause) {
        super(cause);
    }

    public LoadingDialectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
