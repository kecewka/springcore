package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;

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

    // Calls repository to find a trainee by username
    Trainee findTraineeByUsername(String username);

    // Calls repository to delete a trainee by username
    void deleteTraineeByUsername(String username);

    // Calls repository to find a trainees training list by username and training name
    List<Training> findTrainingByUsernameAndCriteria(String username, String trainingName);

    // Calls repository to update Trainees trainers list
    void updateTraineesTrainerList(Long id, List<Trainer> trainersList);

    // Calls repository to change trainees password
    void changeTraineePassword(Long id, String password);

    // Calls repository to change Activate
    void activateTrainee(Long id);

    // Calls repository to change Deactivate
    void deactivateTrainee(Long id);
}
