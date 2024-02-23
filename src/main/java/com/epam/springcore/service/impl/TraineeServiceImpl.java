package com.epam.springcore.service.impl;

import com.epam.springcore.dto.training.TraineeTrainingCriteriaDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.exception.TraineeNotFoundException;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.repository.specifications.TrainingSpecifications;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.epam.springcore.repository.specifications.SpecificationUtils.byField;

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
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all trainees", transactionId);
        return traineeRepository.findAll();
    }


    @Override
    @Transactional
    public Trainee findTraineeById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding trainee with ID: {}", transactionId, id);

        return traineeRepository.findById(id).orElseThrow(() -> new TraineeNotFoundException("Trainee with id: " + id + " not found"));

    }

    @Override
    @Transactional
    public Trainee findTraineeByUsername(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding trainee with Username: {}", transactionId, username);
        User user = userService.findUserByUsername(username);

        return traineeRepository.findByUserId(user.getId()).orElseThrow(() -> new TraineeNotFoundException("Trainee with username: " + username + " not found"));

    }

    @Override
    @Transactional
    public Trainee createTrainee(Trainee trainee) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Creating trainee: {}", transactionId, trainee);
        userService.createUser(trainee.getUser());
        return traineeRepository.save(trainee);
    }

    @Override
    @Transactional
    public Trainee updateTrainee(Trainee trainee) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating trainee: {}", transactionId, trainee);
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
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting trainee with ID: {}", transactionId, id);
        traineeRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void deleteTraineeByUsername(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting trainee with username: {}", transactionId, username);
        Trainee trainee = findTraineeByUsername(username);
        traineeRepository.deleteById(trainee.getId());
    }


    @Override
    @Transactional
    public List<Training> findTrainingByUsernameAndCriteria(TraineeTrainingCriteriaDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] finding training by username and criteria", transactionId);
        Trainee trainee = findTraineeByUsername(dto.getUsername());

        Specification<Training> spec = Specification.where(
                        byField(TrainingSpecifications::hasTraineeId, trainee.getId()))
                .and(byField(TrainingSpecifications::hasTrainingType, dto.getTrainingType()))
                .and(byField(TrainingSpecifications::hasTrainingDateAfter, dto.getFrom()))
                .and(byField(TrainingSpecifications::hasTrainingDateBefore, dto.getTo()))
                .and(byField(TrainingSpecifications::hasTrainerId, trainerService.findTrainerByUsername(dto.getTrainerName()).getId()));
        return trainingRepository.findAll(spec);

    }


    @Override
    @Transactional
    public List<Trainer> updateTraineesTrainerList(String username, List<String> trainers) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] updating trainer list of trainee with username: {}", transactionId, username);
        List<Trainer> convertedTrainers = new ArrayList<>();
        for (String s : trainers) {
            convertedTrainers.add(trainerService.findTrainerByUsername(s));
        }
        Trainee existingTrainee = findTraineeByUsername(username);
        existingTrainee.setTrainers(convertedTrainers);
        updateTrainee(existingTrainee);

        List<Trainer> responseTrainers = new ArrayList<>();
        List<Long> trainerIds = traineeRepository.findTrainersByTraineeId(existingTrainee.getId());
        for (Long l : trainerIds) {
            responseTrainers.add(trainerService.findTrainerById(l));
        }

        return responseTrainers;
    }

    @Override
    @Transactional
    public void changeTraineePassword(Long id, String password) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] updating password of trainee with id: {}", transactionId, id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setPassword(password);
        updateTrainee(trainee);
    }

    @Override
    @Transactional
    public void activateTrainee(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] activating trainee with id: {}", transactionId, id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setActive(true);
        updateTrainee(trainee);
    }

    @Override
    @Transactional
    public void deactivateTrainee(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] deactivating trainee with id: {}", transactionId, id);
        Trainee trainee = findTraineeById(id);
        trainee.getUser().setActive(false);
        updateTrainee(trainee);
    }

}