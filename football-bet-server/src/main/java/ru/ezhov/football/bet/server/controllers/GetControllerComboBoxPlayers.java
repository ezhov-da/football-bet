package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.domain.Players;
import ru.ezhov.football.bet.server.util.FromSession;
import ru.ezhov.football.bet.server.util.NotFoundValueInSession;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerComboBoxPlayers implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        try {
            List<Player> players =
                    new Players()
                            .players(
                                    new FromSession(request)
                                            .player()
                                            .getUsername()
                            );
            List<ControllerPlayer> controllerPlayers = new ArrayList<>();
            for (Player player : players) {
                controllerPlayers.add(
                        new ControllerPlayer(
                                player.getDoubleUsername(),
                                player.getDoubleFio()
                        )
                );
            }
            return new Gson().toJson(new ControllerComboBoxPlayers(controllerPlayers));
        } catch (NotFoundValueInSession notFoundValueInSession) {
            notFoundValueInSession.printStackTrace();
            return "not found player";
        }
    }

    public static class ControllerComboBoxPlayers {
        private List<ControllerPlayer> players;

        public ControllerComboBoxPlayers(List<ControllerPlayer> players) {
            this.players = players;
        }
    }

    public static class ControllerPlayer {
        private String doubleUsername;
        private String doubleFio;

        public ControllerPlayer(String doubleUsername, String doubleFio) {
            this.doubleUsername = doubleUsername;
            this.doubleFio = doubleFio;
        }
    }

}
