package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TraineeRepository {
    // Calls database to get a list of all Records in trainees table
    List<Trainee> findAll();

    // Calls database to get a single Record in trainees table which matches id
    Trainee findById(Long id);

    // Calls database to create a record in trainees table
    void createTrainee(Trainee trainee);

    // Calls database to update a record in trainees table
    void updateTrainee(Trainee trainee);

    // Calls database to delete a single Record in trainees table which matches id
    void deleteTrainee(Long id);

}
