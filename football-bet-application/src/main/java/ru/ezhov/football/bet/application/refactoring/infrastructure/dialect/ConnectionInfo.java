package ru.ezhov.football.bet.application.refactoring.infrastructure.dialect;

public interface ConnectionInfo {
    String getUrl();

    String getUsername();

    String getPassword();

    String getClassDriver();
}
