package com.epam.springcore.repository.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineeRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {

    private final BasicDataSource basicDataSource;

    @Autowired
    public TraineeRepositoryImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public List<Trainee> findAll() {
        List<Trainee> trainees = new ArrayList<>();
        String query = "select tr.*, u.* from trainees tr" +
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

                Trainee trainee = new Trainee();
                trainee.setId(rs.getLong("tr.id"));
                trainee.setAddress(rs.getString("u.address"));
                trainee.setTrainers(new ArrayList<Trainer>());
                trainee.setUser(user);
                trainee.setTrainings(new ArrayList<Training>());
                trainee.setDateOfBirth(rs.getDate("tr.date_of_birth").toLocalDate());

                trainees.add(trainee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainees;
    }

    @Override
    public Trainee findById(Long id) {
        Trainee trainee = new Trainee();
        String query = "select tr.*, u.* from trainees tr" +
                "join users u on tr.user_id = u.id" +
                "where tr.id = ? ";
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

                trainee.setId(rs.getLong("tr.id"));
                trainee.setAddress(rs.getString("u.address"));
                trainee.setTrainers(new ArrayList<Trainer>());
                trainee.setUser(user);
                trainee.setTrainings(new ArrayList<Training>());
                trainee.setDateOfBirth(rs.getDate("tr.date_of_birth").toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trainee;
    }

    @Override
    public void createTrainee(Trainee trainee) {
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
