package com.epam.springcore.service.impl;

import com.epam.springcore.entity.Training;
import com.epam.springcore.exception.TrainingNotFoundException;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainingService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all trainings", transactionId);
        return trainingRepository.findAll();
    }

    @Override
    public Training findTrainingById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding training with ID: {}", transactionId, id);

        return trainingRepository.findById(id).orElseThrow(() -> new TrainingNotFoundException("Training with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void createTraining(Training training) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Creating training: {}", transactionId, training);
        trainingRepository.save(training);
    }

    @Override
    public void updateTraining(Training training) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating training: {}", transactionId, training);
        trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting training with ID: {}", transactionId, id);
        trainingRepository.deleteById(id);
    }
}
