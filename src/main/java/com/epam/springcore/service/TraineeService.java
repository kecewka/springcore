package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;

import java.util.List;

public interface TraineeService {
    List<Trainee> getAllTrainees();
    Trainee findTraineeById(Long id);
    void createTrainee(Trainee trainee);
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);
}
