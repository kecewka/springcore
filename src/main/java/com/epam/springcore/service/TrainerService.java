package com.epam.springcore.service;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainerService {
    // Calls repository to get List of All Trainers
    List<Trainer> getAllTrainers();

    // Calls repository to get Trainer by id
    Trainer findTrainerById(Long id);

    // Calls repository and User Service to create a Trainer
    void createTrainer(Trainer trainer);

    // Calls repository to update a Trainer
    void updateTrainer(Trainer trainer);

    // Calls repository to delete a Trainer by id
    void deleteTrainer(Long id);

    // Calls repository to find a Trainer by username
    Trainer findTrainerByUsername(String username);

    // Calls repository to delete a trainer by username
    void deleteTrainerByUsername(String username);

    // Calls repository to find a trainees training list by username and training name
    List<Training> findTrainingByUsernameAndCriteria(String username, String trainingName);

    // Calls repository to change trainers password
    void changeTrainerPassword(Long id, String password);

    // Calls repository to change Activate trainer
    void activateTrainee(Long id);

    // Calls repository to change Deactivate trainer
    void deactivateTrainee(Long id);

    // Calls repository to get all the trainers who were not assigned to trainees
    List<Trainer> getNotAssignedAndActiveTrainers();
}





