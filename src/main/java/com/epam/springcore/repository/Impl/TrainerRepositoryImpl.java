package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TrainerRepository;
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
public class TrainerRepositoryImpl implements TrainerRepository {

    private final BasicDataSource basicDataSource;
    private static final Logger logger = LogManager.getLogger(TrainerRepositoryImpl.class);


    @Autowired
    public TrainerRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Trainer> findAll() {

        logger.info("Accessing database to find all trainers");

        List<Trainer> trainers = new ArrayList<>();
        String query = "select tr.id AS trainer_id, " +
                "s.id AS specialization_id, s.training_type_name, " +
                "u.id AS user_id, u.first_name, u.last_name, u.username, u.password, u.is_active " +
                "from trainers tr " +
                "join training_types s on tr.specialization = s.id " +
                "join users u on tr.user_id = u.id";
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

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("specialization_id"));
                trainingType.setName(rs.getString("training_type_name"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("trainer_id"));
                trainer.setSpecialization(trainingType);
                trainer.setUser(user);

                trainers.add(trainer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainers;
    }

    @Override
    public Trainer findById(Long id) {

        logger.info("Accessing database to find a trainer with id: {}", id);

        Trainer trainer = new Trainer();
        String query = "select tr.id AS trainer_id, " +
                "s.id AS specialization_id, s.training_type_name, " +
                "u.id AS user_id, u.first_name, u.last_name, u.username, u.password, u.is_active " +
                "from trainers tr " +
                "join training_types s on tr.specialization = s.id " +
                "join users u on tr.user_id = u.id " +
                "where tr.id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("is_active"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("specialization_id"));
                trainingType.setName(rs.getString("training_type_name"));

                trainer.setId(rs.getLong("trainer_id"));
                trainer.setUser(user);
                trainer.setSpecialization(trainingType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    @Override
    public void createTrainer(Trainer trainer) {

        logger.info("Adding trainer to the database");

        String query = "insert into trainers (specialization, user_id) values (?, ?)";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, trainer.getSpecialization().getId());
            statement.setLong(2, trainer.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTrainer(Trainer trainer) {

        logger.info("updating a trainer in a database");

        String query = "update trainers set specialization = ?, user_id = ? where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, trainer.getSpecialization().getId());
            statement.setLong(2, trainer.getUser().getId());
            statement.setLong(3, trainer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTrainer(Long id) {

        logger.info("deleting a trainee with id: {} from database", id);

        String query = "delete from trainers where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
