package com.epam.springcore.repository;

import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainingRepository {
    List<Training> findAll();
    Training findById(Long id);
    void createTraining(Training training);
    void updateTraining(Training training);
    void deleteTraining(Long id);

}