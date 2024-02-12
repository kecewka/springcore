package com.epam.springcore.service;

import com.epam.springcore.dto.training.TraineeTrainingCriteriaDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;

import java.util.List;

public interface TraineeService {

    /**
     * Retrieves a list of all trainees.
     *
     * @return A list of all trainees.
     */
    List<Trainee> getAllTrainees();

    /**
     * Retrieves a trainee by their unique identifier.
     *
     * @param id The unique identifier of the trainee.
     * @return The trainee with the specified ID, or null if not found.
     */
    Trainee findTraineeById(Long id);

    /**
     * Creates a new trainee using the provided information.
     *
     * @param trainee The trainee to be created.
     */
    Trainee createTrainee(Trainee trainee);

    /**
     * Updates the information of an existing trainee.
     *
     * @param trainee The trainee with updated information.
     */
    Trainee updateTrainee(Trainee trainee);

    /**
     * Deletes a trainee by their unique identifier.
     *
     * @param id The unique identifier of the trainee to be deleted.
     */
    void deleteTrainee(Long id);

    /**
     * Retrieves a trainee by their username.
     *
     * @param username The username of the trainee.
     * @return The trainee with the specified username, or null if not found.
     */
    Trainee findTraineeByUsername(String username);

    /**
     * Deletes a trainee by their username.
     *
     * @param username The username of the trainee to be deleted.
     */
    void deleteTraineeByUsername(String username);

    /**
     * Retrieves the training list for a trainee based on username, training name, and date.
     *
     * @param traineeTrainingCriteriaDTO The DTO containing criterias.
     * @return A list of trainings that match the criteria.
     */
    List<Training> findTrainingByUsernameAndCriteria(TraineeTrainingCriteriaDTO traineeTrainingCriteriaDTO);

    /**
     * Updates the list of trainers for a specific trainee.
     *
     * @param id           The unique identifier of the trainee.
     * @param trainersList The updated list of trainers for the trainee.
     */
    void updateTraineesTrainerList(Long id, List<Trainer> trainersList);

    /**
     * Changes the password for a specific trainee.
     *
     * @param id       The unique identifier of the trainee.
     * @param password The new password for the trainee.
     */
    void changeTraineePassword(Long id, String password);

    /**
     * Activates a trainee account.
     *
     * @param id The unique identifier of the trainee.
     */
    void activateTrainee(Long id);

    /**
     * Deactivates a trainee account.
     *
     * @param id The unique identifier of the trainee.
     */
    void deactivateTrainee(Long id);

    /**
     * Retrieves a list of trainers by username of user.
     *
     * @param username The username of the trainee.
     * @return The list of Trainers
     */
    List<Trainer> findTrainersList(String username);
}
