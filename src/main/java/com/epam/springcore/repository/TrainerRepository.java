package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;

import java.util.List;

public interface TrainerRepository {
    // Calls database to get a list of all Records in trainers table
    List<Trainer> findAll();

    // Calls database to get a single Record in trainers table which matches id
    Trainer findById(Long id);

    // Calls database to create a record in trainers table
    void createTrainer(Trainer trainer);

    // Calls database to update a record in trainers table
    void updateTrainer(Trainer trainer);

    // Calls database to delete a single Record in trainers table which matches id
    void deleteTrainer(Long id);

}