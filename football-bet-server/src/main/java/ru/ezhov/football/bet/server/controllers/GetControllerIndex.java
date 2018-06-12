package ru.ezhov.football.bet.server.controllers;

import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.infrastructure.AdminService;
import ru.ezhov.football.bet.server.util.FromSession;
import ru.ezhov.football.bet.server.util.NotFoundValueInSession;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class GetControllerIndex implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        try {
            Player player = new FromSession(request).player();
            Map<String, Object> model = new HashMap<>();
            model.put("isAdmin", AdminService.getInstance().isAdmin(player.getUsername()));
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "index.html")
            );
        } catch (NotFoundValueInSession notFoundValueInSession) {
            notFoundValueInSession.printStackTrace();
            return "Не авторизован";
        }
    }
}
