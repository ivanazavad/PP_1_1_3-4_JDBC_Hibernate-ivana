package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static SessionFactory factory;//TODO убираем лишние комментарии
    private static final String DRIVER = "java.sql.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my179sql";

    static {
        try {
            factory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

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
