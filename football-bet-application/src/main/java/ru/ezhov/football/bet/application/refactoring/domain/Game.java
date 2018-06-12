package ru.ezhov.football.bet.application.refactoring.domain;

public class Game {
    private int id;
    private String game = "";
    private String date = "";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
