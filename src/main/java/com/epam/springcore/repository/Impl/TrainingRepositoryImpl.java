package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.repository.TrainingRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    private final BasicDataSource basicDataSource;

    @Autowired
    public TrainingRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Training> findAll() {
        List<Training> trainings = new ArrayList<>();
        String query = "select t.*, tr.*, tre.*, ty.*" +
                "from trainings t" +
                "join trainees tr on t.trainee_id = tr.id" +
                "join trainers tre on t.trainer_id = tre.id" +
                "join training_types ty on t.training_type_id = try.id";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("tr.id"));
                trainee.setDateOfBirth(rs.getDate("tr.date_of_birth").toLocalDate());
                trainee.setAddress(rs.getString("tr.address"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("tre.id"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("ty.id"));
                trainingType.setName(rs.getString("ty.name"));

                Training training = new Training();
                training.setId(rs.getLong("t.id"));
                training.setTrainingName(rs.getString("t.training_name"));
                training.setTrainingDate(rs.getDate("t.training_date").toLocalDate());
                training.setTrainingDuration(rs.getLong("t.training_duration"));
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
        Training training = new Training();
        String query = "select t.*, tr.*, tre.*, ty.*" +
                "from trainings t" +
                "join trainees tr on t.trainee_id = tr.id" +
                "join trainers tre on t.trainer_id = tre.id" +
                "join training_types ty on t.training_type_id = try.id" +
                "where t.id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("tr.id"));
                trainee.setDateOfBirth(rs.getDate("tr.date_of_birth").toLocalDate());
                trainee.setAddress(rs.getString("tr.address"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("tre.id"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("ty.id"));
                trainingType.setName(rs.getString("ty.name"));

                training.setId(rs.getLong("t.id"));
                training.setTrainingName(rs.getString("t.training_name"));
                training.setTrainingDate(rs.getDate("t.training_date").toLocalDate());
                training.setTrainingDuration(rs.getLong("t.training_duration"));
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
        String query = "insert into trainings (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)" +
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
        String query = "update trainings set trainee_id = ?, trainer_id = ?, training_name = ?," +
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

