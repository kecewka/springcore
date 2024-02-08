package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Training;
import com.epam.springcore.exception.TrainingNotFoundException;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainingService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private static final Logger LOGGER = LogManager.getLogger(TrainingServiceImpl.class);

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @Override
    public List<Training> getAllTrainings() {
        LOGGER.info("Getting all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public Training findTrainingById(Long id) {
        LOGGER.info("Finding training with ID: {}", id);

        return trainingRepository.findById(id).orElseThrow(() -> new TrainingNotFoundException("Training with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void createTraining(Training training) {
        LOGGER.info("Creating training: {}", training);
        trainingRepository.save(training);
    }

    @Override
    public void updateTraining(Training training) {
        LOGGER.info("Updating training: {}", training);
        trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {
        LOGGER.info("Deleting training with ID: {}", id);
        trainingRepository.deleteById(id);
    }
}
