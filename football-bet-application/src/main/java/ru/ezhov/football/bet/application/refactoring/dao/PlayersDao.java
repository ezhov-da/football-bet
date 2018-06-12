package ru.ezhov.football.bet.application.refactoring.dao;

import ru.ezhov.football.bet.application.refactoring.domain.Player;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayersDao {
    private DataSource dataSource;

    public PlayersDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Player> players(String username) throws Exception {
        String query =
                "Select " +
                        "fio, " +
                        "username, " +
                        "doublefio, " +
                        "doubleusername, " +
                        "chempion " +
                        "From AZ_DEV.dbo.T_E_football_users " +
                        "where username='" + username + "'";

        System.out.println("Список игроков:\n" + query);

        List<Player> players = new ArrayList();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Player myObject = new Player();
                        myObject.setFio(resultSet.getString("fio"));
                        myObject.setUsername(resultSet.getString("username"));
                        myObject.setDoubleFio(resultSet.getString("doublefio"));
                        myObject.setDoubleUsername(resultSet.getString("doubleusername"));
                        myObject.setChempion(resultSet.getString("chempion"));
                        players.add(myObject);
                    }
                    return players;
                }
            }
        }
    }

    public Player newPlayer(String fio, String username, String doubleFio, String doubleUsername, String chempion) {
        throw new UnsupportedOperationException();
    }

    public boolean  existsPlayer(String fio, String username, String doubleFio, String doubleUsername) throws Exception {
        String query = "SELECT" +
                "  [fio],\n" +
                "  [username],\n" +
                "  [doublefio],\n" +
                "  [doubleusername]" +
                " FROM [dbo].[T_E_football_users]\n" +
                "WHERE " +
                " fio = ? " +
                " AND username = ? " +
                " AND doublefio = ? " +
                " AND doubleusername = ? ";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, fio);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, doubleFio);
                preparedStatement.setString(4, doubleUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }
    }

    public Player playerBy(String username) throws Exception {
        String query = "SELECT" +
                "  [fio],\n" +
                "  [username],\n" +
                "  [doublefio],\n" +
                "  [doubleusername],\n" +
                "  [chempion]\n" +
                "FROM [dbo].[T_E_football_users]\n" +
                "WHERE username = ?;";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Player player = new Player();
                        player.setFio(resultSet.getString("fio"));
                        player.setUsername(resultSet.getString("username"));
                        player.setDoubleFio(resultSet.getString("doublefio"));
                        player.setDoubleUsername(resultSet.getString("doubleusername"));
                        player.setChempion(resultSet.getString("chempion"));
                        return player;
                    } else {
                        throw new IllegalArgumentException("Пользователь: [" + username + "] не найден");
                    }
                }
            }
        }
    }

    public Player authPlayer(String username, String password) throws Exception {
        String query = "SELECT USERNAME FROM dbo.T_E_football_users WHERE USERNAME = ? AND PASSWORD = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return playerBy(username);
                    } else {
                        throw new IllegalArgumentException("Пользователь не авторизован");
                    }
                }
            }
        }
    }

    public Player save(String fio,
                       String username,
                       String password,
                       String doubleFio,
                       String doubleUsername,
                       String champion) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO [dbo].[T_E_football_users] (\n" +
                    "  [fio]\n" +
                    "  , [username]\n" +
                    "  , [PASSWORD]\n" +
                    "  , [doublefio]\n" +
                    "  , [doubleusername]\n" +
                    "  , [chempion]\n" +
                    ") VALUES (\n" +
                    "  ?,\n" +
                    "  ?,\n" +
                    "  ?,\n" +
                    "  ?,\n" +
                    "  ?,\n" +
                    "  ?\n" +
                    ");";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, fio);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, doubleFio);
                preparedStatement.setString(5, doubleUsername);
                preparedStatement.setString(6, champion);

                preparedStatement.execute();

                return playerBy(username);
            }
        }
    }
}
