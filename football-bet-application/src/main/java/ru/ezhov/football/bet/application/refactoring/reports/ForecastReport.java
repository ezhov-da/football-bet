package ru.ezhov.football.bet.application.refactoring.reports;

import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ForecastReport {
    private DataSource dataSource;

    public ForecastReport(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Report> get(String insteadUsername, String originalUsername) throws ReportException {
        String query = "Select \n" +
                " CONVERT(VarChar(50), t1.data, 104) as \"Дата игры\",\n" +
                " t1.gamer as \"Играющие команды\",\n" +
                " cast(t3.userstrana1 as varchar(10)) + ' - ' + cast(t3.userstrana2 as varchar(10)) as \"Прогноз игрока\"\n" +
                "From AZ_DEV.dbo.T_E_football_respisanie t1\n" +
                "join AZ_DEV.dbo.T_E_football_userSchet t3 on t1.id=t3.idmatcha\n" +
                "join AZ_DEV.dbo.T_E_football_users t4 on t4.doubleusername = t3.username \n" +
                "where t4.doubleusername = '" + insteadUsername + "' and t4.username = '" + originalUsername + "'\n" +
                "order by  t1.data,t1.gamer,t4.doublefio";


        List<Report> reports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareCall(query);) {
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        reports.add(new Report(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
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
        private String gameDate;
        private String games;
        private String forecast;

        public Report(String gameDate, String games, String forecast) {
            this.gameDate = gameDate;
            this.games = games;
            this.forecast = forecast;
        }

        public String getGameDate() {
            return gameDate;
        }

        public String getGames() {
            return games;
        }

        public String getForecast() {
            return forecast;
        }
    }
}
