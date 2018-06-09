package ru.ezhov.football.bet.application.refactoring.dao;

import ru.ezhov.football.bet.application.refactoring.domain.Player;

import java.sql.Connection;
import java.util.List;

public class PlayersDao {
    private Connection connection;

    public PlayersDao(Connection connection) {
        this.connection = connection;
    }

    public List<Player> players() throws Exception {
//        List<Player> players = new ArrayList();
//        Statement st = connection.createStatement();
//
//        ResultSet rs = st.executeQuery(zapros);
//
//        while (rs.next()) {
//            Player myObject = new Player();
//            myObject.setFio(rs.getString("fio"));
//            myObject.setUsername(rs.getString("username"));
//            myObject.setDoubleFio(rs.getString("doublefio"));
//            myObject.setDoubleUsername(rs.getString("doubleusername"));
//            myObject.setChempion(rs.getString("chempion"));
//
//            players.add(myObject);
//        }
//        return players;
        throw new UnsupportedOperationException();
    }

    public Player save(Player player) {
        throw new UnsupportedOperationException();
    }
}
