package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;

import java.util.List;

public interface TrainerRepository {
    List<Trainer> findAll();
    Trainer findById(Long id);
    void createTrainer(Trainer trainer);
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);

}