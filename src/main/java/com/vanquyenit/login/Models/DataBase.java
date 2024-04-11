package com.vanquyenit.chatapplication.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class
DataBase {
    private static DataBase instance;
    private Connection connection;

    private DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/chatapp", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

