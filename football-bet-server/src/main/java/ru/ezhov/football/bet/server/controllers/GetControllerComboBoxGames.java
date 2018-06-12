package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.domain.Game;
import ru.ezhov.football.bet.application.refactoring.domain.Games;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerComboBoxGames implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        String player = request.queryParams("player");
        if (player == null || "".equals(player)) {
            throw new IllegalArgumentException("Не указан игрок");
        }
        Games games = new Games();
        List<Game> gameList = games.getGames(player);
        List<ControllerGame> controllerGames = new ArrayList<>();
        for (Game game : gameList) {
            controllerGames.add(new ControllerGame(game.getId(), game.getDate(), game.getGame()));
        }
        return new Gson().toJson(new ControllerComboBoxGames(controllerGames));
    }

    public class ControllerComboBoxGames {
        List<ControllerGame> games;

        public ControllerComboBoxGames(List<ControllerGame> games) {
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
