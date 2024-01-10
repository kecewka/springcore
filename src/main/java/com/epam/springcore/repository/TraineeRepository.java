package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;

import java.util.List;

public interface TraineeRepository {
    List<Trainee> findAll();
    Trainee findById(Long id);
    void createTrainee(Trainee trainee);
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);

}
