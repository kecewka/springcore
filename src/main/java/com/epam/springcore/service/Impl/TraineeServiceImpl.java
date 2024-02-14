package com.epam.springcore.service.Impl;

import com.epam.springcore.dto.training.TraineeTrainingCriteriaDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.exception.TraineeNotFoundException;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.epam.springcore.repository.specifications.TrainingSpecifications.*;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final TrainingRepository trainingRepository;
    private final UserService userService;
    private final TrainerService trainerService;
    private static final Logger LOGGER = LogManager.getLogger(TraineeServiceImpl.class);

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, TrainingRepository trainingRepository, UserService userService, TrainerService trainerService) {
        this.traineeRepository = traineeRepository;
        this.trainingRepository = trainingRepository;
        this.userService = userService;
        this.trainerService = trainerService;
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

        return traineeRepository.findById(id).orElseThrow(() -> new TraineeNotFoundException("Trainee with id: " + id + " not found"));

    }

    @Override
    @Transactional
    public Trainee findTraineeByUsername(String username) {
        LOGGER.info("Finding trainee with Username: {}", username);
        User user = userService.findUserByUsername(username);

        return traineeRepository.findByUserId(user.getId()).orElseThrow(() -> new TraineeNotFoundException("Trainee with username: " + username + " not found"));

    }

    @Override
    @Transactional
    public Trainee createTrainee(Trainee trainee) {
        LOGGER.info("Creating trainee: {}", trainee);
        userService.createUser(trainee.getUser());
        return traineeRepository.save(trainee);
    }

    @Override
    @Transactional
    public Trainee updateTrainee(Trainee trainee) {
        LOGGER.info("Updating trainee: {}", trainee);
        Trainee existingTrainee = findTraineeByUsername(trainee.getUser().getUsername());
        existingTrainee.setAddress(trainee.getAddress());
        existingTrainee.setDateOfBirth(trainee.getDateOfBirth());

        User updatedUser = trainee.getUser();
        User existingUser = existingTrainee.getUser();
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setActive(updatedUser.isActive());

        return traineeRepository.save(existingTrainee);
    }

    @Override
    @Transactional
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
    public List<Training> findTrainingByUsernameAndCriteria(TraineeTrainingCriteriaDTO dto) {
        LOGGER.info("finding training by username and criteria");
        Trainee trainee = findTraineeByUsername(dto.getUsername());

        if (dto.getTrainingType() == null && dto.getFrom() == null && dto.getTo() == null && dto.getTrainerName() == null) {
            return trainingRepository.findAll(hasTraineeId(trainee.getId()));
        }
        if (dto.getTrainingType() == null && dto.getTo() == null && dto.getTrainerName() == null) {
            return trainingRepository.findAll(hasTraineeId(trainee.getId())
                    .and(hasTrainingDateAfter(dto.getFrom())));
        }
        if (dto.getTrainingType() == null && dto.getFrom() == null && dto.getTrainerName() == null) {
            return trainingRepository.findAll(hasTraineeId(trainee.getId())
                    .and(hasTrainingDateBefore(dto.getTo())));
        }
        if (dto.getTrainingType() == null && dto.getTrainerName() == null) {
            return trainingRepository.findAll(hasTraineeId(trainee.getId())
                    .and(hasTrainingDateBetween(dto.getFrom(), dto.getTo())));
        }
        if (dto.getTrainingType() == null && dto.getFrom() == null && dto.getTo() == null) {
            Trainer trainer = trainerService.findTrainerByUsername(dto.getTrainerName());
            return trainingRepository.findAll(hasTraineeId(trainee.getId())
                    .and(hasTrainerId(trainer.getId())));
        }
        return trainingRepository.findAll(hasTraineeId(trainee.getId())
                .and(hasTrainingType(dto.getTrainingType()))
                .and(hasTrainingDateBetween(dto.getFrom(), dto.getTo()))
                .and(hasTrainerId(trainerService.findTrainerByUsername(dto.getTrainerName()).getId())));

    }


    @Override
    @Transactional
    public List<Trainer> updateTraineesTrainerList(String username, List<String> trainers) {
        LOGGER.info("updating trainer list of trainee with username: {}", username);
        List<Trainer> convertedTrainers = new ArrayList<>();
        for (String s : trainers) {
            convertedTrainers.add(trainerService.findTrainerByUsername(s));
        }
        Trainee existingTrainee = findTraineeByUsername(username);
        existingTrainee.setTrainers(convertedTrainers);
        updateTrainee(existingTrainee);

        List<Trainer> responseTrainers = new ArrayList<>();
        List<Long> trainerIds = traineeRepository.findTrainersByTrainerId(existingTrainee.getId());
        for (Long l : trainerIds) {
            responseTrainers.add(trainerService.findTrainerById(l));
        }

        return responseTrainers;
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
