package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee findTraineeById(Long id) {
        return traineeRepository.findById(id);
    }

    @Override
    public void createTrainee(Trainee trainee) {
        traineeRepository.createTrainee(trainee);
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeRepository.updateTrainee(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeRepository.deleteTrainee(id);
    }
}
