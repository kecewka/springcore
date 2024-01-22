package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineeRepository;
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
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(TraineeServiceImpl.class);

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        LOGGER.info("Getting all trainees");
        return traineeRepository.findAll();
    }

    @Override
    public Trainee findTraineeById(Long id) {
        LOGGER.info("Finding trainee with ID: {}", id);
        Trainee trainee = null;
        Optional<Trainee> optional = traineeRepository.findById(id);
        if (optional.isPresent()) {
            trainee = optional.get();
        }

        return trainee;

    }

    @Transactional
    public Trainee findTraineeByUsername(String username) {
        LOGGER.info("Finding trainee with ID: {}", username);
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
}
