package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.User;
import com.epam.springcore.repository.UserRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final BasicDataSource basicDataSource;
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Autowired
    public UserRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<User> findAll() {

        logger.info("Accessing database to find all users");

        List<User> users = new ArrayList<>();
        String query = "select * from users";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isActive = rs.getBoolean("is_active");
                User user = new User(id, firstName, lastName, username, password, isActive);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }

    @Override
    public User findById(Long id) {

        logger.info("Accessing database to find a user with id: {}", id);

        User user = new User();
        String query = "select * from users where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {

        logger.info("Accessing database to find a user with username: {}", username);

        User user = new User();
        String query = "select * from users where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean saveUser(User user) {

        logger.info("Adding user to the database");

        String query = "insert into users (first_name, last_name, username, password, is_active) values (?,?,?,?,?)";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isActive());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateUser(User user) {

        logger.info("updating a training in a database");

        String query = "update users set first_name = ?, last_name = ?, username = ?, password = ?, is_active = ? where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isActive());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(Long id) {

        logger.info("deleting a trainee with id: {} from database", id);

        String query = "delete from users where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean usernameExists(String username) {

        logger.info("checking if user with username: {} exists", username);

        String query = "SELECT COUNT(*) AS count FROM users WHERE username = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

