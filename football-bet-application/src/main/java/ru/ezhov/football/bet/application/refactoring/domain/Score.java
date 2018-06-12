package ru.ezhov.football.bet.application.refactoring.domain;

import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

    private DataSource dataSource;

    public Score() {
        this.dataSource = new DataSource();
    }

    public void addPlayer(String doubleUsername, String gameId, int one, int two) throws ScoreException {
        //Этот код будет вносить данные в таблицу, но перед эти, будем проверять =)

        try {
            if (one < 0 || two < 0)
                throw new ScoreException("Айяяй, что за счет [" + one + " : " + one + " ], внеси корректный!");

            //Этот код будет проверять время внесения, если время меньше 9:00 и больше 18:00, тогда бэньг!
            Date timeFirst = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 9:00:00");
            Date timeLast = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 18:00:00");
            Date timeNow = new Date();

            if (timeFirst.compareTo(timeNow) > 0 || timeLast.compareTo(timeNow) < 0)
                throw new ScoreException("Ая яй, как не хорошо вносить счет не по времени =)");

            try (Connection connection = dataSource.getConnection()) {
                try (Statement st = connection.createStatement();) {
                    try (ResultSet rs =
                                 st.executeQuery(
                                         "Select userstrana1 " +
                                                 "from AZ_DEV.dbo.T_E_football_userSchet " +
                                                 "where username='" + doubleUsername + "' and idmatcha = '" + gameId + "'");) {

                        while (rs.next()) {
                            throw new ScoreException("Вы уже вносили счет по указанной игре!");
                        }
                    }
                    st.execute("insert into AZ_DEV.dbo.T_E_football_userSchet values('" + doubleUsername + "','" + gameId + "','" + one + "','" + two + "')");
                }
            }
        } catch (Exception ex) {
            throw new ScoreException(ex.getMessage());
        }
    }

    public void addGame(String gameId, int one, int two) throws ScoreException {
        //Этот код будет вносить данные в таблицу, но перед эти, будем проверять =)
        try {
            if (one < 0 || two < 0)
                throw new ScoreException("Айяяй, что за счет [" + one + " : " + one + " ], внеси корректный!");

            //Этот код будет проверять время внесения, если время меньше 9:00 и больше 18:00, тогда бэньг!
            Date timeFirst = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 9:00:00");
            Date timeLast = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 18:00:00");
            Date timeNow = new Date();

            if (timeFirst.compareTo(timeNow) > 0 || timeLast.compareTo(timeNow) < 0)
                throw new ScoreException("Ая яй, как не хорошо вносить счет не по времени =)");

            try (Connection connection = dataSource.getConnection()) {
                try (Statement st = connection.createStatement();) {
                    try (ResultSet rs =
                                 st.executeQuery(
                                         "Select idmatcha " +
                                                 "from AZ_DEV.dbo.T_E_football_itogschet " +
                                                 "where idmatcha = '" + gameId + "'");) {

                        while (rs.next()) {
                            throw new ScoreException("Вы уже вносили счет по указанной игре!");
                        }
                    }
                    st.execute("insert into AZ_DEV.dbo.T_E_football_itogschet(idmatcha, strana1, strana2) values('" + gameId + "','" + one + "','" + two + "')");
                }
            }
        } catch (Exception ex) {
            throw new ScoreException(ex.getMessage());
        }
    }
}
