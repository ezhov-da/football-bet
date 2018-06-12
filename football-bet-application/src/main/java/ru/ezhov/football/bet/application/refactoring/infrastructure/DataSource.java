package ru.ezhov.football.bet.application.refactoring.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
//            //*********************************MSSQL************************************
//            String user = "sa";
//            String password = "sa";
//            Class.forName("org.h2.Driver");
//            // Загружаем драйвер (регистрируем себя)
//            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/football", user, password);
//            //**********************************************************************

        String dbUrl1 = "localhost\\SQLEXPRESS:1433";
        String user = "SAZ_ADM";
        String password = "Ffdnjpfrfp123456789";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Загружаем драйвер (регистрируем себя)
        connection = DriverManager.getConnection("jdbc:sqlserver://" + dbUrl1 + ";databaseName=AZ_DEV;user=" + user + ";password = " + password);

        return connection;
    }
}
