package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.repository.TrainingRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                //trainee.set
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainings;
    }

    @Override
    public Training findById(Long id) {
        return null;
    }

    @Override
    public void createTraining(Training training) {

    }

    @Override
    public void updateTraining(Training training) {

    }

    @Override
    public void deleteTraining(Long id) {

    }
}
