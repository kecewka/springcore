package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Training;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private static final Logger logger = LogManager.getLogger(TrainingServiceImpl.class);

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @Override
    public List<Training> getAllTrainings() {
        logger.info("Getting all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public Training findTrainingById(Long id) {
        logger.info("Finding training with ID: {}", id);
        return trainingRepository.findById(id);
    }

    @Override
    public void createTraining(Training training) {
        logger.info("Creating trainer: {}", training);
        trainingRepository.createTraining(training);
    }

    @Override
    public void updateTraining(Training training) {
        logger.info("Updating trainer: {}", training);
        trainingRepository.updateTraining(training);
    }

    @Override
    public void deleteTraining(Long id) {
        logger.info("Deleting training with ID: {}", id);
        trainingRepository.deleteTraining(id);
    }
}
