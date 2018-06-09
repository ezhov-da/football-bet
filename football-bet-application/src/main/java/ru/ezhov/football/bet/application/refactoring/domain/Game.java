package ru.ezhov.football.bet.application.refactoring.domain;

public class Game {
    private int id;
    private String game="";
    private String date="";

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    @Override
    public String toString() {
        return date + "     " + game;
    }

}
