package com.epam.springcore.service;

import com.epam.springcore.dto.training.TrainerTrainingCriteriaDTO;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainerService {

    /**
     * Retrieves a list of all trainers.
     *
     * @return A list of all trainers.
     */
    List<Trainer> getAllTrainers();

    /**
     * Retrieves a trainer by their unique identifier.
     *
     * @param id The unique identifier of the trainer.
     * @return The trainer with the specified ID, or null if not found.
     */
    Trainer findTrainerById(Long id);

    /**
     * Creates a new trainer using the provided information.
     *
     * @param trainer The trainer to be created.
     */
    Trainer createTrainer(Trainer trainer);

    /**
     * Updates the information of an existing trainer.
     *
     * @param trainer The trainer with updated information.
     */
    Trainer updateTrainer(Trainer trainer);

    /**
     * Deletes a trainer by their unique identifier.
     *
     * @param id The unique identifier of the trainer to be deleted.
     */
    void deleteTrainer(Long id);

    /**
     * Retrieves a trainer by their username.
     *
     * @param username The username of the trainer.
     * @return The trainer with the specified username, or null if not found.
     */
    Trainer findTrainerByUsername(String username);

    /**
     * Deletes a trainer by their username.
     *
     * @param username The username of the trainer to be deleted.
     */
    void deleteTrainerByUsername(String username);

    /**
     * Retrieves the training list for a trainer based on username, training name, and date.
     *
     * @param trainerTrainingCriteriaDTO The DTO containing criterias.
     * @return A list of trainings that match the criteria.
     */
    List<Training> findTrainingByUsernameAndCriteria(TrainerTrainingCriteriaDTO trainerTrainingCriteriaDTO);

    /**
     * Changes the password for a specific trainer.
     *
     * @param id       The unique identifier of the trainer.
     * @param password The new password for the trainer.
     */
    void changeTrainerPassword(Long id, String password);

    /**
     * Activates a trainer account.
     *
     * @param id The unique identifier of the trainer.
     */
    void activateTrainer(Long id);

    /**
     * Deactivates a trainer account.
     *
     * @param id The unique identifier of the trainer.
     */
    void deactivateTrainer(Long id);

    /**
     * Retrieves a list of trainers who are not assigned to any trainees and are active.
     *
     * @return A list of not assigned and active trainers.
     */
    List<Trainer> getNotAssignedAndActiveTrainers();
}





