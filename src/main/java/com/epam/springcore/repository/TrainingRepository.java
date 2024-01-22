package com.epam.springcore.repository;

import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainingRepository {
    // Calls database to get a list of all Records in trainings table
    List<Training> findAll();

    // Calls database to get a single Record in trainings table which matches id
    Training findById(Long id);

    // Calls database to create a record in trainings table
    void createTraining(Training training);

    // Calls database to update a record in trainings table
    void updateTraining(Training training);

    // Calls database to delete a single Record in trainings table which matches id
    void deleteTraining(Long id);

}