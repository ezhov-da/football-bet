package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

public class XmlDialectException extends Exception {

    public XmlDialectException() {
    }

    public XmlDialectException(String message) {
        super(message);
    }

    public XmlDialectException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlDialectException(Throwable cause) {
        super(cause);
    }

    public XmlDialectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
