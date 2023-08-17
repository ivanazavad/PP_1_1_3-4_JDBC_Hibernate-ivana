package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydb.users ("
            + " id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(30) NOT NULL,"
            + "lastName VARCHAR(30) NOT NULL,"
            + "age INT)";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS mydb.users";
    private final String SAVE_USER = "INSERT INTO mydb.users (name, lastName, age) VALUES (?, ?, ?)";
    private final String REMOVE_USER_BY_ID = "DELETE FROM mydb.users WHERE id = ?";
    private final String GET_ALL_USERS = "SELECT * FROM mydb.users";
    private final String CLEAN_TABLE = "TRUNCATE TABLE mydb.users";
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() {
        try {
            PreparedStatement statement = connection.prepareStatement(DROP_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try {
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
        try {
            PreparedStatement statement = connection.prepareStatement(REMOVE_USER_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
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
        try {
            PreparedStatement statement = connection.prepareStatement(CLEAN_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
