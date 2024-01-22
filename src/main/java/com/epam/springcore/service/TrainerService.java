package com.epam.springcore.service;

import com.epam.springcore.entity.Trainer;

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
}




