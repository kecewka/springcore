package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Training;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Training findTrainingById(Long id) {
        return trainingRepository.findById(id);
    }

    @Override
    public void createTraining(Training training) {
        trainingRepository.createTraining(training);
    }

    @Override
    public void updateTraining(Training training) {
        trainingRepository.updateTraining(training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteTraining(id);
    }
}
