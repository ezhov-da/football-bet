package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.domain.Game;
import ru.ezhov.football.bet.application.refactoring.domain.Games;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerComboBoxAllGames implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        Games games = new Games();
        List<Game> gameList = games.getGames();
        List<ControllerGame> controllerGames = new ArrayList<>();
        for (Game game : gameList) {
            controllerGames.add(new ControllerGame(game.getId(), game.getDate(), game.getGame()));
        }
        return new Gson().toJson(new ControllerComboBoxAllGames(controllerGames));
    }

    public class ControllerComboBoxAllGames {
        List<ControllerGame> games;

        public ControllerComboBoxAllGames(List<ControllerGame> games) {
            this.games = games;
        }
    }

    public class ControllerGame {
        private int id;
        private String game;

        public ControllerGame(int id, String date, String game) {
            this.id = id;
            this.game = date + " - " + game;
        }
    }
}

