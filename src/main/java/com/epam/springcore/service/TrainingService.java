package com.epam.springcore.service;

import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainingService {
    // Calls repository to get List of All Trainings
    List<Training> getAllTrainings();

    // Calls repository to get Training by id
    Training findTrainingById(Long id);

    // Calls repository to create a Training
    void createTraining(Training training);

    // Calls repository to update a Training
    void updateTraining(Training training);

    // Calls repository to delete a Training by id
    void deleteTraining(Long id);
}
