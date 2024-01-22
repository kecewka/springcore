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
    private static final Logger LOGGER = LogManager.getLogger(TrainerServiceImpl.class);

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        LOGGER.info("Getting all trainers");
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findTrainerById(Long id) {
        Trainer trainer = null;
        LOGGER.info("Finding trainer with ID: {}", id);
        return trainer;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        LOGGER.info("Creating trainer: {}", trainer);
        userService.createUser(trainer.getUser());
        trainerRepository.save(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        LOGGER.info("Updating trainer: {}", trainer);
        trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        LOGGER.info("Deleting trainer with ID: {}", id);
        trainerRepository.deleteById(id);
    }
}
