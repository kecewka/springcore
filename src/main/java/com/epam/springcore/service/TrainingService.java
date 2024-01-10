package com.epam.springcore.service;

import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();
    Training findTrainingById(Long id);
    void createTraining(Training training);
    void updateTraining(Training training);
    void deleteTraining(Long id);
}