package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.repository.TrainerRepository;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        logger.info("Getting all trainers");
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findTrainerById(Long id) {
        logger.info("Finding trainer with ID: {}", id);
        return trainerRepository.findById(id);
    }

    @Override
    public void createTrainer(Trainer trainer) {
        logger.info("Creating trainer: {}", trainer);
        userService.createUser(trainer.getUser());
        trainerRepository.createTrainer(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        logger.info("Updating trainer: {}", trainer);
        trainerRepository.updateTrainer(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        logger.info("Deleting trainer with ID: {}", id);
        trainerRepository.deleteTrainer(id);
    }
}
