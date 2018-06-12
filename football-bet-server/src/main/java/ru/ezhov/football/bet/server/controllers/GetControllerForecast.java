package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;
import ru.ezhov.football.bet.application.refactoring.reports.ForecastReport;
import ru.ezhov.football.bet.server.util.FromSession;
import ru.ezhov.football.bet.server.util.NotFoundValueInSession;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerForecast implements Route {
    @Override
    public String handle(Request request, Response response) throws Exception {
        response.type("application/json");
        try {
            Player player = new FromSession(request).player();
            ForecastReport forecastReport = new ForecastReport(new DataSource());
            List<ForecastReport.Report> reports = forecastReport.get(player.getDoubleUsername(), player.getUsername());
            List<ControllerForecast> controllerForecasts = new ArrayList<>();
            for (ForecastReport.Report report : reports) {
                controllerForecasts.add(
                        new ControllerForecast(
                                report.getGameDate(),
                                report.getGames(),
                                report.getForecast()
                        )
                );
            }
            return new Gson().toJson(new ControllerReport(controllerForecasts));
        } catch (NotFoundValueInSession notFoundValueInSession) {
            response.status(401);
            return "not found player";
        }
    }

    public static class ControllerReport {
        List<ControllerForecast> forecasts;

        public ControllerReport(List<ControllerForecast> forecasts) {
            this.forecasts = forecasts;
        }

        public List<ControllerForecast> getGames() {
            return forecasts;
        }
    }

    public static class ControllerForecast {
        public String gameDate;
        public String game;
        public String forecast;

        public ControllerForecast(String gameDate, String game, String forecast) {
            this.gameDate = gameDate;
            this.game = game;
            this.forecast = forecast;
        }
    }
}
