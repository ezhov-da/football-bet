package ru.ezhov.football.bet.application.refactoring.reports;

import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GamesResultReport {
    private DataSource dataSource;

    public GamesResultReport(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Report> get() throws ReportException {
        String query = "SELECT \n" +
                " CONVERT(VarChar(50), t1.data, 104) AS \"Дата игры\",\n" +
                " t1.gamer AS \"Играющие команды\",\n" +
                " t4.doublefio AS \"Игрок\",\n" +
                "  cast(t2.strana1 AS VARCHAR(10)) + ' - ' +  cast(t2.strana2 AS VARCHAR(10))  AS \"Итоговый счет\",\n" +
                "  cast(t3.userstrana1 AS VARCHAR(10)) + ' - ' +  cast(t3.userstrana2 AS VARCHAR(10)) AS \"Прогноз игрока\",\n" +
                " CASE WHEN t2.strana1 = t3.userstrana1 AND t2.strana2 = t3.userstrana2 THEN 3\n" +
                "	  WHEN abs(t2.strana1 - t2.strana2) =  abs(t3.userstrana1 - t3.userstrana2) AND ((t2.strana1 >= t2.strana2 AND t3.userstrana1 >= t3.userstrana2) OR (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)) THEN 2\n" +
                "	  WHEN (t2.strana1 > t2.strana2 AND t3.userstrana1 > t3.userstrana2) OR (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2) THEN 1\n" +
                "	  ELSE 0 END AS \"Очки\"\n" +
                "	  \n" +
                "FROM AZ_DEV.dbo.T_E_football_respisanie t1\n" +
                "JOIN AZ_DEV. dbo.T_E_football_itogschet t2 ON t1.id=t2.idmatcha\n" +
                "JOIN AZ_DEV.dbo.T_E_football_userSchet t3 ON t1.id=t3.idmatcha\n" +
                "JOIN (SELECT DISTINCT doubleusername, doublefio FROM AZ_DEV.dbo.T_E_football_users) t4 ON t4.doubleusername = t3.username\n" +
                "ORDER BY  1 DESC,2,3";

        List<Report> reports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareCall(query);) {
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        reports.add(new Report(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        ));
                    }
                }
            }
            return reports;
        } catch (Exception ex) {
            throw new ReportException(ex);
        }

    }

    public static class Report {
        private String date;
        private String game;
        private String player;
        private String resulScore;
        private String forecast;
        private String score;

        public Report(String date, String game, String player, String resulScore, String forecast, String score) {
            this.date = date;
            this.game = game;
            this.player = player;
            this.resulScore = resulScore;
            this.forecast = forecast;
            this.score = score;
        }

        public String getDate() {
            return date;
        }

        public String getGame() {
            return game;
        }

        public String getPlayer() {
            return player;
        }

        public String getResulScore() {
            return resulScore;
        }

        public String getForecast() {
            return forecast;
        }

        public String getScore() {
            return score;
        }
    }

}
