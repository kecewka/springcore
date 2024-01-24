package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final TrainingRepository trainingRepository;
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(TraineeServiceImpl.class);

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, TrainingRepository trainingRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.trainingRepository = trainingRepository;
        this.userService = userService;
    }


    @Override
    @Transactional
    public List<Trainee> getAllTrainees() {
        LOGGER.info("Getting all trainees");
        return traineeRepository.findAll();
    }


    @Override
    @Transactional
    public Trainee findTraineeById(Long id) {
        LOGGER.info("Finding trainee with ID: {}", id);
        Trainee trainee = null;
        Optional<Trainee> optional = traineeRepository.findById(id);
        if (optional.isPresent()) {
            trainee = optional.get();
        }

        return trainee;

    }

    @Override
    @Transactional
    public Trainee findTraineeByUsername(String username) {
        LOGGER.info("Finding trainee with Username: {}", username);
        Trainee trainee = null;
        User user = userService.getUserByUsername(username);
        Optional<Trainee> optional = traineeRepository.findByUserId(user.getId());
        if (optional.isPresent()) {
            trainee = optional.get();
        }

        return trainee;

    }

    @Override
    @Transactional
    public void createTrainee(Trainee trainee) {
        LOGGER.info("Creating trainee: {}", trainee);
        userService.createUser(trainee.getUser());
        traineeRepository.save(trainee);
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        LOGGER.info("Updating trainee: {}", trainee);
        traineeRepository.save(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        LOGGER.info("Deleting trainee with ID: {}", id);
        traineeRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void deleteTraineeByUsername(String username) {
        LOGGER.info("Deleting trainee with username: {}", username);
        Trainee trainee = findTraineeByUsername(username);
        traineeRepository.deleteById(trainee.getId());
    }


    @Override
    @Transactional
    public List<Training> findTrainingByUsernameAndCriteria(String username, String trainingName) {
        LOGGER.info("finding training by username and criteria");
        Trainee trainee = findTraineeByUsername(username);
        return trainingRepository.findByUsernameAndCriteria(trainee.getId(), trainingName);
    }

    @Override
    @Transactional
    public void updateTraineesTrainerList(Long id, List<Trainer> trainersList) {
        LOGGER.info("updating trainer list of trainee with id: {}", id);
        Trainee trainee = findTraineeById(id);
        trainee.setTrainers(trainersList);
        updateTrainee(trainee);
    }

    @Override
    @Transactional
    public void changeTraineePassword(Long id, String password) {
        LOGGER.info("updating password of trainee with id: {}", id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setPassword(password);
        updateTrainee(trainee);
    }

    @Override
    @Transactional
    public void activateTrainee(Long id) {
        LOGGER.info("activating trainee with id: {}", id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setActive(true);
        updateTrainee(trainee);
    }

    @Override
    @Transactional
    public void deactivateTrainee(Long id) {
        LOGGER.info("deactivating trainee with id: {}", id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setActive(false);
        updateTrainee(trainee);
    }
}
