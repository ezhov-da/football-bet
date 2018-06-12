package ru.ezhov.football.bet.server.controllers;

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
        Map<String, Object> model = new HashMap<>();
        return new VelocityTemplateEngine().render(
                new ModelAndView(model, "index.html")
        );
    }
}
