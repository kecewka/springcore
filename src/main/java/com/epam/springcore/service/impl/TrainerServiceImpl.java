package com.epam.springcore.service.impl;

import com.epam.springcore.dto.training.TrainerTrainingCriteriaDTO;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.exception.TrainerNotFoundException;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.repository.TrainerRepository;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.repository.specifications.TrainingSpecifications;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.springcore.repository.specifications.SpecificationUtils.byField;


@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;
    private final TraineeRepository traineeRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LogManager.getLogger(TrainerServiceImpl.class);

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, TrainingRepository trainingRepository, TraineeRepository traineeRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        this.traineeRepository = traineeRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<Trainer> getAllTrainers() {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all trainers", transactionId);
        return trainerRepository.findAll();
    }

    @Override
    @Transactional
    public Trainer findTrainerById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding trainer with ID: {}", transactionId, id);

        return trainerRepository.findById(id).orElseThrow(() -> new TrainerNotFoundException("Trainer with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public Trainer createTrainer(Trainer trainer) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Creating trainer: {}", transactionId, trainer);
        userService.createUser(trainer.getUser());
        Trainer copyTrainer = copyTrainer(trainer);
        String hashedPassword = passwordEncoder.encode(trainer.getUser().getPassword());
        trainer.getUser().setPassword(hashedPassword);
        trainerRepository.save(trainer);
        return copyTrainer;
    }

    @Override
    @Transactional
    public Trainer updateTrainer(Trainer trainer) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating trainer: {}", transactionId, trainer);
        Trainer existingTrainer = findTrainerByUsername(trainer.getUser().getUsername());
        existingTrainer.setSpecialization(trainer.getSpecialization());

        User updatedUser = trainer.getUser();
        User existingUser = existingTrainer.getUser();
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setActive(updatedUser.isActive());

        return trainerRepository.save(existingTrainer);
    }

    @Override
    @Transactional
    public void deleteTrainer(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting trainer with ID: {}", transactionId, id);
        trainerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Trainer findTrainerByUsername(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding trainer with Username: {}", transactionId, username);
        User user = userService.findUserByUsername(username);

        return trainerRepository.findByUserId(user.getId()).orElseThrow(() -> new TrainerNotFoundException("Trainer with username: " + username + " not found"));

    }

    @Override
    @Transactional
    public void deleteTrainerByUsername(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting trainer with username: {}", transactionId, username);
        Trainer trainer = findTrainerByUsername(username);
        trainerRepository.deleteById(trainer.getId());
    }

    @Override
    @Transactional
    public List<Training> findTrainingByUsernameAndCriteria(TrainerTrainingCriteriaDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] finding training by username and criteria", transactionId);
        Trainer trainer = findTrainerByUsername(dto.getUsername());

        Specification<Training> spec = Specification.where(
                byField(TrainingSpecifications::hasTrainerId, trainer.getId())
                        .and(byField(TrainingSpecifications::hasTrainingDateAfter, dto.getFrom()))
                        .and(byField(TrainingSpecifications::hasTrainingDateBefore, dto.getTo()))
                        .and(byField(TrainingSpecifications::hasTraineeId, traineeRepository.findByUserId(userService.findUserByUsername(dto.getUsername()).getId()).get().getId())));
        return trainingRepository.findAll(spec);

    }

    @Override
    @Transactional
    public void changeTrainerPassword(Long id, String password) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] updating password of trainer with id: {}", transactionId, id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setPassword(password);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public void activateTrainer(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] activating trainer with id: {}", transactionId, id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setActive(true);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public void deactivateTrainer(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] deactivating trainer with id: {}", transactionId, id);
        Trainer trainer = findTrainerById(id);
        trainer.getUser().setActive(false);
        updateTrainer(trainer);
    }

    @Override
    @Transactional
    public List<Trainer> getNotAssignedAndActiveTrainers() {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] finding all active trainers without trainees", transactionId);
        List<Trainer> list = trainerRepository.findAllActiveTrainersWithoutTrainees();
        System.out.println(list);
        return list;
    }

    @Override
    public Trainer copyTrainer(Trainer trainer) {
        User copyUser = new User();
        copyUser.setUsername(trainer.getUser().getUsername());
        copyUser.setPassword(trainer.getUser().getPassword());
        Trainer copyTrainer = new Trainer();
        copyTrainer.setUser(copyUser);
        return copyTrainer;
    }
}
