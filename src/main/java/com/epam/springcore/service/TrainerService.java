package com.epam.springcore.service;

import com.epam.springcore.entity.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> getAllTrainers();
    Trainer findTrainerById(Long id);
    void createTrainer(Trainer trainer);
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);
}