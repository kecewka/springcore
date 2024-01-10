package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TrainerRepository;
import org.apache.commons.dbcp2.BasicDataSource;
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

    @Autowired
    public TrainerRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Trainer> findAll() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "select tr*, s.*, u.* from trainers tr" +
                "join training_types s on tr.specialization = s.id " +
                "join users u on tr.user_id = u.id";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("u.id"));
                user.setFirstName(rs.getString("u.first_name"));
                user.setLastName(rs.getString("u.last_name"));
                user.setUsername(rs.getString("u.username"));
                user.setPassword(rs.getString("u.password"));
                user.setActive(rs.getBoolean("u.is_active"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("s.id"));
                trainingType.setName(rs.getString("s.training_type_name"));

                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("tr.id"));
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
        Trainer trainer = new Trainer();
        String query = "select tr*, s.*, u.* from trainers tr" +
                "join training_types s on tr.specialization = s.id " +
                "join users u on tr.user_id = u.id where id = ? ";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("u.id"));
                user.setFirstName(rs.getString("u.first_name"));
                user.setLastName(rs.getString("u.last_name"));
                user.setUsername(rs.getString("u.username"));
                user.setPassword(rs.getString("u.password"));
                user.setActive(rs.getBoolean("u.is_active"));

                TrainingType trainingType = new TrainingType();
                trainingType.setId(rs.getLong("s.id"));
                trainingType.setName(rs.getString("s.training_type_name"));

                trainer.setId(rs.getLong("rs.id"));
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
