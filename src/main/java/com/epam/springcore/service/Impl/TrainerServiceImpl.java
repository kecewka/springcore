package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.exception.TrainerNotFoundException;
import com.epam.springcore.repository.TrainerRepository;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.epam.springcore.repository.specifications.TrainingSpecifications.*;
import static com.epam.springcore.repository.specifications.TrainingSpecifications.hasTrainingDate;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(TrainerServiceImpl.class);

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, TrainingRepository trainingRepository, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<Trainer> getAllTrainers() {
        LOGGER.info("Getting all trainers");
        return trainerRepository.findAll();
    }

    @Override
    @Transactional
    public Trainer findTrainerById(Long id) {
        LOGGER.info("Finding trainer with ID: {}", id);

        return trainerRepository.findById(id).orElseThrow(() -> new TrainerNotFoundException("Trainer with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void createTrainer(Trainer trainer) {
        LOGGER.info("Creating trainer: {}", trainer);
        userService.createUser(trainer.getUser());
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void updateTrainer(Trainer trainer) {
        LOGGER.info("Updating trainer: {}", trainer);
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void deleteTrainer(Long id) {
        LOGGER.info("Deleting trainer with ID: {}", id);
        trainerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Trainer findTrainerByUsername(String username) {
        LOGGER.info("Finding trainer with Username: {}", username);
        User user = userService.findUserByUsername(username);

        return trainerRepository.findByUserId(user.getId()).orElseThrow(() -> new TrainerNotFoundException("Trainer with username: " + username + " not found"));

    }

    @Override
    @Transactional
    public void deleteTrainerByUsername(String username) {
        LOGGER.info("Deleting trainer with username: {}", username);
        Trainer trainer = findTrainerByUsername(username);
        trainerRepository.deleteById(trainer.getId());
    }

    @Override
    @Transactional
    public List<Training> findTrainingByUsernameAndCriteria(String username, String trainingName, LocalDate trainingDate) {
        LOGGER.info("finding training by username and criteria");
        Trainer trainer = findTrainerByUsername(username);
        if (trainingName == null && trainingDate == null) {
            return trainingRepository.findAll(hasTrainerId(trainer.getId()));
        }
        if (trainingName == null) {
            return trainingRepository.findAll(hasTrainerId(trainer.getId())
                    .and(hasTrainingDate(trainingDate)));
        }
        if (trainingDate == null) {
            return trainingRepository.findAll(hasTrainerId(trainer.getId())
                    .and(hasTrainingName(trainingName)));
        }
        return trainingRepository.findAll(hasTrainerId(trainer.getId())
                .and(hasTrainingName(trainingName))
                .and(hasTrainingDate(trainingDate)));
    }

    @Override
    @Transactional
    public void changeTrainerPassword(Long id, String password) {
        LOGGER.info("updating password of trainer with id: {}", id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setPassword(password);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public void activateTrainer(Long id) {
        LOGGER.info("activating trainer with id: {}", id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setActive(true);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public void deactivateTrainer(Long id) {
        LOGGER.info("deactivating trainer with id: {}", id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setActive(false);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public List<Trainer> getNotAssignedAndActiveTrainers() {
        LOGGER.info("finding all active trainers without trainees");
        return trainerRepository.findAllActiveTrainersWithoutTrainees();
    }
}
