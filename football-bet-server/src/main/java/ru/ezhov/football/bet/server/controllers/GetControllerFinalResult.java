package ru.ezhov.football.bet.server.controllers;

import com.google.gson.Gson;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;
import ru.ezhov.football.bet.application.refactoring.reports.FinalResultReport;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetControllerFinalResult implements Route {
    @Override
    public String handle(Request request, Response response) throws Exception {
        response.type("application/json");

        FinalResultReport finalResultReport = new FinalResultReport(new DataSource());
        List<FinalResultReport.Report> reports = finalResultReport.get();

        List<ControllerFinalResult> controllerFinalResults = new ArrayList<>();
        for (FinalResultReport.Report report : reports) {
            controllerFinalResults.add(
                    new ControllerFinalResult(
                            report.getGamer(),
                            report.getWinner(),
                            report.getScore(),
                            report.getPlusFive(),
                            report.getFinalScore()
                    )
            );
        }

        return new Gson().toJson(new ControllerReport(controllerFinalResults));


    }

    public static class ControllerReport {
        List<ControllerFinalResult> results;

        public ControllerReport(List<ControllerFinalResult> results) {
            this.results = results;
        }

        public List<ControllerFinalResult> getGames() {
            return results;
        }
    }

    public static class ControllerFinalResult {
        public String player;
        public String winner;
        public String score;
        public String plusFive;
        public String finalScore;

        public ControllerFinalResult(String player, String winner, String score, String plusFive, String finalScore) {
            this.player = player;
            this.winner = winner;
            this.score = score;
            this.plusFive = plusFive;
            this.finalScore = finalScore;
        }
    }
}
