package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;
import ru.ezhov.football.bet.application.refactoring.reports.GamesResultReport;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerGameResult implements Route {
    @Override
    public String handle(Request request, Response response) throws Exception {
        response.type("application/json");

        GamesResultReport gamesResultReport = new GamesResultReport(new DataSource());
        List<GamesResultReport.Report> reportList = gamesResultReport.get();

        List<ControllerReportGame> controllerReportGames = new ArrayList<>();
        for (GamesResultReport.Report report : reportList) {
            controllerReportGames.add(
                    new ControllerReportGame(
                            report.getDate(),
                            report.getGame(),
                            report.getPlayer(),
                            report.getResulScore(),
                            report.getForecast(),
                            report.getScore()
                    )
            );
        }

        return new Gson().toJson(new ControllerReport(controllerReportGames));
    }


    public static class ControllerReport {
        List<ControllerReportGame> games;

        public ControllerReport(List<ControllerReportGame> games) {
            this.games = games;
        }

        public List<ControllerReportGame> getGames() {
            return games;
        }
    }

    public static class ControllerReportGame {
        public String gameDate;
        public String game;
        public String gamer;
        public String result;
        public String gamerForecast;
        public String score;

        public ControllerReportGame(String gameDate, String game, String gamer, String result, String gamerForecast, String score) {
            this.gameDate = gameDate;
            this.game = game;
            this.gamer = gamer;
            this.result = result;
            this.gamerForecast = gamerForecast;
            this.score = score;
        }
    }


}
