package ru.ezhov.football.bet.application.refactoring.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static Connection connection;

    public static synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            //*********************************MSSQL************************************
            String user = "sa";
            String password = "sa";
            Class.forName("org.h2.Driver");
            // Загружаем драйвер (регистрируем себя)
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/football", user, password);
            //**********************************************************************
        }
        return connection;
    }
}
