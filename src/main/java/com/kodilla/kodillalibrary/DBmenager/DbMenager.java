package com.kodilla.kodillalibrary.DBmenager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbMenager {

    INSTANCE;

    private Connection connection;

    DbMenager() {
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "library_user");
        connectionProperties.put("password", "library_Pass123");

        try {

          connection = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/library" +
                  "?serverTimezone=Europe/Warsaw" +
                  "&useSSL=False&allowPublicKeyRetrieval=true",
                  connectionProperties);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbMenager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
