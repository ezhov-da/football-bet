package ru.ezhov.football.bet.application.refactoring.domain;

public class NewPlayer {
    private String fio;
    private String username;
    private String password;
    private String doubleFio;
    private String doubleUsername;
    private String winner;

    public NewPlayer(String fio, String username, String password, String doubleFio, String doubleUsername, String winner) {
        this.fio = fio;
        this.username = username;
        this.password = password;
        this.doubleFio = doubleFio;
        this.doubleUsername = doubleUsername;
        this.winner = winner;
    }

    public String getFio() {
        return fio;
    }

    public String getUsername() {
        return username;
    }

    public String getDoubleFio() {
        return doubleFio;
    }

    public String getDoubleUsername() {
        return doubleUsername;
    }

    public String getWinner() {
        return winner;
    }

    public String getPassword() {
        return password;
    }
}
