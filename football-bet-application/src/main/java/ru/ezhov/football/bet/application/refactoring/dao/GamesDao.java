package ru.ezhov.football.bet.application.refactoring.dao;

import ru.ezhov.football.bet.application.refactoring.domain.Game;

import java.sql.Connection;
import java.util.List;

public class GamesDao {
    private Connection connection;

    public GamesDao(Connection connection) {
        this.connection = connection;
    }

    public List<Game> games() throws Exception {
//        List<Game> spisok = new ArrayList<>();
//
//        Statement st = connection.createStatement();
//
//        ResultSet rs = st.executeQuery(zapros);
//
//        while (rs.next()) {
//            Game myObject = new Game();
//            myObject.setId(rs.getInt("id"));
//            myObject.setDate(rs.getString("data"));
//            myObject.setGame(rs.getString("gamer"));
//            spisok.add(myObject);
//        }
//        if (spisok.size() == 0) {
//            Game myObject = new Game();
//            myObject.setId(0);
//            myObject.setDate("");
//            myObject.setGame("");
//            spisok.add(myObject);
//        }
//        return spisok;
        throw new UnsupportedOperationException();
    }
}
