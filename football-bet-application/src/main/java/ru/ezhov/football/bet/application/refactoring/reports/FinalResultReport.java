package ru.ezhov.football.bet.application.refactoring.reports;

import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FinalResultReport {
    private DataSource dataSource;

    public FinalResultReport(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Report> get() throws ReportException {
        String query = "SELECT \n" +
                "t1.doublefio AS \"Игрок\",\n" +
                "t1.chempion AS \"Чемпион\",\n" +
                "t1.point AS \"Очки\",\n" +
                "t1.chemp AS \"+5 за чемпиона\",\n" +
                "t1.chemp +  t1.point AS \"Итоговые очки\" \n" +
                "FROM (\n" +
                "		SELECT \n" +
                "		t4.doublefio,\n" +
                "		CASE WHEN t4.chempion = '' THEN 'Россия' ELSE t4.chempion END AS chempion,\n" +
                "		 sum(CASE WHEN t2.strana1 = t3.userstrana1 AND t2.strana2 = t3.userstrana2 THEN 3\n" +
                "			  WHEN abs(t2.strana1 - t2.strana2) =  abs(t3.userstrana1 - t3.userstrana2) AND ((t2.strana1 >= t2.strana2 AND t3.userstrana1 >= t3.userstrana2) OR (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)) THEN 2\n" +
                "			  WHEN (t2.strana1 > t2.strana2 AND t3.userstrana1 > t3.userstrana2) OR (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2) THEN 1\n" +
                "			  ELSE 0 END) AS point,\n" +
                "		CASE WHEN   t4.chempion = 'Германия' THEN 5 ELSE 0 END AS chemp  \n" +
                "\n" +
                "		FROM AZ_DEV.dbo.T_E_football_respisanie t1\n" +
                "		JOIN AZ_DEV. dbo.T_E_football_itogschet t2 ON t1.id=t2.idmatcha\n" +
                "		JOIN AZ_DEV.dbo.T_E_football_userSchet t3 ON t1.id=t3.idmatcha\n" +
                "		JOIN (SELECT DISTINCT doubleusername, doublefio, chempion FROM AZ_DEV.dbo.T_E_football_users) t4 ON t4.doubleusername = t3.username\n" +
                "		GROUP BY  t4.doublefio,t4.chempion\n" +
                ") t1\n" +
                "WHERE t1.doublefio<>'Ежов Денис Анатольевич' ORDER BY  5 DESC, 1;";

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
                                resultSet.getString(5)
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
        private String gamer;
        private String winner;
        private String score;
        private String plusFive;
        private String finalScore;

        public Report(String gamer, String winner, String score, String plusFive, String finalScore) {
            this.gamer = gamer;
            this.winner = winner;
            this.score = score;
            this.plusFive = plusFive;
            this.finalScore = finalScore;
        }

        public String getGamer() {
            return gamer;
        }

        public String getWinner() {
            return winner;
        }

        public String getScore() {
            return score;
        }

        public String getPlusFive() {
            return plusFive;
        }

        public String getFinalScore() {
            return finalScore;
        }
    }
}
