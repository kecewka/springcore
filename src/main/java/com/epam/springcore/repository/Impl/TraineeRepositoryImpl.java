package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineeRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {

    private final BasicDataSource basicDataSource;
    private static final Logger logger = LogManager.getLogger(TraineeRepositoryImpl.class);

    @Autowired
    public TraineeRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Trainee> findAll() {

        logger.info("Accessing database to find all trainees");

        List<Trainee> trainees = new ArrayList<>();
        String query = "SELECT tr.id AS trainee_id, tr.date_of_birth, tr.address, " +
                "u.id AS user_id, u.first_name, u.last_name, u.username, u.password, u.is_active " +
                "FROM trainees tr " +
                "JOIN users u ON tr.user_id = u.id ";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("is_active"));

                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("trainee_id"));
                trainee.setAddress(rs.getString("address"));
                trainee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                trainee.setUser(user);
                trainee.setTrainers(new ArrayList<>());
                trainee.setTrainings(new ArrayList<>());

                trainees.add(trainee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainees;
    }

    @Override
    public Trainee findById(Long id) {

        logger.info("Accessing database to find a trainee with id: {}", id);

        Trainee trainee = new Trainee();
        String query = "SELECT tr.id AS trainee_id, tr.date_of_birth, tr.address, " +
                "u.id AS user_id, u.first_name, u.last_name, u.username, u.password, u.is_active " +
                "FROM trainees tr " +
                "JOIN users u ON tr.user_id = u.id " +
                "WHERE tr.id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getBoolean("is_active"));

                    trainee.setId(rs.getLong("trainee_id"));
                    trainee.setAddress(rs.getString("address"));
                    trainee.setUser(user);
                    trainee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainee;
    }


    @Override
    public void createTrainee(Trainee trainee) {

        logger.info("Adding trainee to the database");

        String query = "insert into trainees (date_of_birth, address, user_id) values (?,?,?)";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(trainee.getDateOfBirth()));
            statement.setString(2, trainee.getAddress());
            statement.setLong(3, trainee.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateTrainee(Trainee trainee) {

        logger.info("updating a trainee in a database");

        String query = "update trainees set date_of_birth = ?, address = ?, user_id = ? where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(trainee.getDateOfBirth()));
            statement.setString(2, trainee.getAddress());
            statement.setLong(3, trainee.getUser().getId());
            statement.setLong(4, trainee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrainee(Long id) {

        logger.info("deleting a trainee with id: {} from database", id);

        String query = "delete from trainees where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
