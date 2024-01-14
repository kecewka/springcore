package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.repository.TrainingRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    private final BasicDataSource basicDataSource;
    private static final Logger logger = LogManager.getLogger(TrainingRepositoryImpl.class);

    @Autowired
    public TrainingRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Training> findAll() {

        logger.info("Accessing database to find all trainings");

        List<Training> trainings = new ArrayList<>();
        String query = "SELECT t.id AS training_id, t.training_name, t.training_date, t.training_duration, " +
                "tr.id AS trainee_id, tr.date_of_birth, tr.address, " +
                "tre.id AS trainer_id, " +
                "ty.id AS training_type_id, ty.training_type_name " +
                "from trainings t " +
                "join trainees tr on t.trainee_id = tr.id " +
                "join trainers tre on t.trainer_id = tre.id " +
                "join training_types ty on t.training_type_id = ty.id";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("trainee_id"));
                trainee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                trainee.setAddress(rs.getString("address"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("trainer_id"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("training_type_id"));
                trainingType.setName(rs.getString("training_type_name"));

                Training training = new Training();
                training.setId(rs.getLong("training_id"));
                training.setTrainingName(rs.getString("training_name"));
                training.setTrainingDate(rs.getDate("training_date").toLocalDate());
                training.setTrainingDuration(rs.getLong("training_duration"));
                training.setTrainingType(trainingType);
                training.setTrainee(trainee);
                training.setTrainer(trainer);

                trainings.add(training);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainings;
    }

    @Override
    public Training findById(Long id) {

        logger.info("Accessing database to find a training with id: {}", id);

        Training training = new Training();
        String query = "SELECT t.id AS training_id, t.training_name, t.training_date, t.training_duration, " +
                "tr.id AS trainee_id, tr.date_of_birth, tr.address, " +
                "tre.id AS trainer_id, " +
                "ty.id AS training_type_id, ty.training_type_name " +
                "from trainings t " +
                "join trainees tr on t.trainee_id = tr.id " +
                "join trainers tre on t.trainer_id = tre.id " +
                "join training_types ty on t.training_type_id = ty.id " +
                "where t.id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("trainee_id"));
                trainee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                trainee.setAddress(rs.getString("address"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("trainer_id"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("training_type_id"));
                trainingType.setName(rs.getString("training_type_name"));

                training.setId(rs.getLong("training_id"));
                training.setTrainingName(rs.getString("training_name"));
                training.setTrainingDate(rs.getDate("training_date").toLocalDate());
                training.setTrainingDuration(rs.getLong("training_duration"));
                training.setTrainingType(trainingType);
                training.setTrainee(trainee);
                training.setTrainer(trainer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return training;
    }

    @Override
    public void createTraining(Training training) {

        logger.info("Adding trainer to the database");

        String query = "insert into trainings (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration) " +
                "values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, training.getTrainee().getId());
            statement.setLong(2, training.getTrainer().getId());
            statement.setString(3, training.getTrainingName());
            statement.setLong(4, training.getTrainingType().getId());
            statement.setDate(5, Date.valueOf(training.getTrainingDate()));
            statement.setLong(6, training.getTrainingDuration());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateTraining(Training training) {

        logger.info("updating a training in a database");

        String query = "update trainings set trainee_id = ?, trainer_id = ?, training_name = ?, " +
                "training_type_id = ?, training_date = ?, training_duration = ? where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, training.getTrainee().getId());
            statement.setLong(2, training.getTrainer().getId());
            statement.setString(3, training.getTrainingName());
            statement.setLong(4, training.getTrainingType().getId());
            statement.setDate(5, Date.valueOf(training.getTrainingDate()));
            statement.setLong(6, training.getTrainingDuration());
            statement.setLong(7, training.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteTraining(Long id) {

        logger.info("deleting a trainee with id: {} from database", id);

        String query = "delete from trainings where id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

