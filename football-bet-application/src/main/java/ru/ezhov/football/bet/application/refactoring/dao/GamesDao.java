package ru.ezhov.football.bet.application.refactoring.dao;

import ru.ezhov.football.bet.application.refactoring.domain.Game;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GamesDao {
    private DataSource dataSource;

    public GamesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Game> games(String insteadUsername) throws Exception {
        String query = " select DISTINCT \n" +
                " t0.id,\n" +
                " CONVERT(VarChar(50), t0.data, 104) as data,\n" +
                " t0.gamer,\n" +
                " t1.* \n" +
                " from \n" +
                "		 (\n" +
                "				  select \n" +
                "				 t0.id,\n" +
                "				 CONVERT(VarChar(50), t0.data, 104) as data,\n" +
                "				 t0.gamer,\n" +
                "				 t1.doubleusername\n" +
                "				 from AZ_DEV.dbo.T_E_football_respisanie t0,AZ_DEV.dbo.T_E_football_users t1\n" +
                "		 ) t0\n" +
                " left join AZ_DEV.dbo.T_E_football_userSchet t1 on t1.idmatcha = t0.id and t1.username=t0.doubleusername\n" +
                " where cast(data as date) between cast(Getdate()+1 as date) and cast(Getdate()+40 as date) and t0.doubleusername = '" + insteadUsername + "' and t1.idmatcha is null\n" +
                " order by data";
        List<Game> games = new ArrayList<>();

        System.out.println("Список игр для игрока:\n" + query);

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Game myObject = new Game();
                        myObject.setId(resultSet.getInt("id"));
                        myObject.setDate(resultSet.getString("data"));
                        myObject.setGame(resultSet.getString("gamer"));
                        games.add(myObject);
                    }
                }
            }
            return games;
        }
    }
}