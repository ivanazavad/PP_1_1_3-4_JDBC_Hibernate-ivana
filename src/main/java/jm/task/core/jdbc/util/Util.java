package jm.task.core.jdbc.util;

import jm.task.core.jdbc.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DRIVER = "java.sql.Driver";
    private static final String USERNAME = "ivana";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String PASSWORD = "Rft8qFPRRXXz";

    public static Connection getConnection() {
       Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка соединения");
        }
        return connection;
    }
}
