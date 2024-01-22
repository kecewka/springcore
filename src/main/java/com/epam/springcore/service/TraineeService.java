package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;

import java.util.List;

public interface TraineeService {
    // Calls repository to get List of All Trainees
    List<Trainee> getAllTrainees();

    // Calls repository to get Trainee by id
    Trainee findTraineeById(Long id);

    // Calls repository and User Service to create a Trainee
    void createTrainee(Trainee trainee);

    // Calls repository to update a trainee
    void updateTrainee(Trainee trainee);

    // Calls repository to delete a trainee by id
    void deleteTrainee(Long id);
}
