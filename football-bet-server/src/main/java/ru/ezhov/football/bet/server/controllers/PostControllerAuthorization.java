package ru.ezhov.football.bet.server.controllers;

import com.google.gson.JsonObject;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.domain.Players;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostControllerAuthorization implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        Session session = request.session(true);
        Player player = session.attribute("player");
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        if (player == null) {
            if ((username == null || "".equals(username) ||
                    password == null || "".equals(password))) {
                response.status(401);
                JsonObject jsonElement = new JsonObject();
                jsonElement.addProperty("errors", "\"Некорректный логин или пароль\"");
                return jsonElement;
            } else {
                player = new Players().authPlayer(username, password);
                session.attribute("player", player);
                JsonObject jsonElement = new JsonObject();
                jsonElement.addProperty("success", true);
                jsonElement.addProperty("redirect", "/");
                return jsonElement;
            }
        } else {
            JsonObject jsonElement = new JsonObject();
            jsonElement.addProperty("success", true);
            jsonElement.addProperty("redirect", "/");
            return jsonElement;
        }
    }
}
