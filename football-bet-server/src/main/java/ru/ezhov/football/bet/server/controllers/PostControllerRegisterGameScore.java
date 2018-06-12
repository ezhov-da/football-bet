package ru.ezhov.football.bet.server.controllers;

import com.google.gson.JsonObject;
import ru.ezhov.football.bet.application.refactoring.domain.Score;
import ru.ezhov.football.bet.application.refactoring.domain.ScoreException;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostControllerRegisterGameScore implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        String game = request.queryParams("game");
        String oneCount = request.queryParams("one");
        String twoCount = request.queryParams("two");

        JsonObject jsonObject = new JsonObject();
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
        return jsonObject;
    }
}
