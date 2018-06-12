package ru.ezhov.football.bet.server.controllers;

import com.google.gson.JsonObject;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.domain.Score;
import ru.ezhov.football.bet.application.refactoring.domain.ScoreException;
import ru.ezhov.football.bet.application.refactoring.infrastructure.AdminService;
import ru.ezhov.football.bet.server.util.FromSession;
import ru.ezhov.football.bet.server.util.NotFoundValueInSession;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostControllerRegisterGameScore implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        JsonObject jsonObject = new JsonObject();
        try {
            Player player = new FromSession(request).player();
            if (AdminService.getInstance().isAdmin(player.getUsername())) {
                String game = request.queryParams("game");
                String oneCount = request.queryParams("one");
                String twoCount = request.queryParams("two");
                if (game == null || "".equals(game) ||
                        oneCount == null || "".equals(oneCount) ||
                        twoCount == null || "".equals(twoCount)) {
                    jsonObject.addProperty("failure", true);
                    jsonObject.addProperty("msg", "Заполните все поля");
                } else {
                    try {
                        int one = Integer.parseInt(oneCount);
                        int two = Integer.parseInt(twoCount);
                        Score score = new Score();
                        score.addGame(game, one, two);
                        jsonObject.addProperty("success", true);
                        jsonObject.addProperty("msg", "Данные внесены");
                    } catch (ScoreException e) {
                        jsonObject.addProperty("failure", true);
                        jsonObject.addProperty("msg", e.getMessage());
                    }
                }
            } else {
                jsonObject.addProperty("failure", true);
                jsonObject.addProperty("msg", "У Вас нет прав для внесения итогов матча");
            }
        } catch (NotFoundValueInSession notFoundValueInSession) {
            notFoundValueInSession.printStackTrace();
            jsonObject.addProperty("failure", true);
            jsonObject.addProperty("msg", "Вы не авторизованы");
        }
        return jsonObject;
    }
}
