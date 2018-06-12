package ru.ezhov.football.bet.server.controllers;

import ru.ezhov.football.bet.server.util.ToSession;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetControllerLogout implements Route {
    @Override
    public String handle(Request request, Response response) throws Exception {
        new ToSession(request).player(null);
        response.redirect("/authorization");
        return "Выход";
    }
}
