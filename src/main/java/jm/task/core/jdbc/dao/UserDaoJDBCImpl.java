package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users ("
            + " id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(30) NOT NULL,"
            + "lastName VARCHAR(30) NOT NULL,"
            + "age INT)";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private final String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String CLEAN_TABLE = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DROP_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_USER);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REMOVE_USER_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CLEAN_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
