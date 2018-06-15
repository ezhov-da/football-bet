package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

public interface DbDialect {
    ConnectionInfo getConnectionInfo();

    QueriesInfo getQueriesInfo();
}
