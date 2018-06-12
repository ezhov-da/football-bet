package ru.ezhov.football.bet.server.controllers;

import com.google.gson.JsonObject;
import ru.ezhov.football.bet.application.refactoring.domain.NewPlayer;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.domain.Players;
import ru.ezhov.football.bet.application.refactoring.domain.PlayersException;
import ru.ezhov.football.bet.server.util.ToSession;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostControllerRegistration implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String fio = request.queryParams("fio");
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String doublefio = request.queryParams("doublefio");
        String doubleusername = request.queryParams("doubleusername");
        String winner = request.queryParams("winner");

        JsonObject jsonObject = new JsonObject();
        if (fio == null || "".equals(fio) ||
                username == null || "".equals(username) ||
                password == null || "".equals(password) ||
                doublefio == null || "".equals(doublefio) ||
                doubleusername == null || "".equals(doubleusername) ||
                winner == null || "".equals(winner)) {
            jsonObject.addProperty("failure", true);
            jsonObject.addProperty("msg", "Заполните все поля");
        } else {
            try {
                Players players = new Players();
                boolean exists = players.existsPlayer(fio, username, doublefio, doubleusername);
                if (exists) {
                    jsonObject.addProperty("failure", true);
                    jsonObject.addProperty("msg", "Пользователь: [" + fio + "/" + username + "] уже создан");
                } else {
                    Player player = players.save(new NewPlayer(fio, username, password, doublefio, doubleusername, winner));
                    jsonObject.addProperty("success", true);
                    jsonObject.addProperty("redirect", "/");
                    new ToSession(request).player(player);
                }
            } catch (PlayersException e) {
                jsonObject.addProperty("failure", true);
                jsonObject.addProperty("msg", e.getMessage());
            }
        }
        return jsonObject;
    }
}
