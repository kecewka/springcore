package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;

import java.time.LocalDate;
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
    void createTrainee(Trainee trainee);

    /**
     * Updates the information of an existing trainee.
     *
     * @param trainee The trainee with updated information.
     */
    void updateTrainee(Trainee trainee);

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
     * @param username     The username of the trainee.
     * @param trainingName The name of the training.
     * @param trainingDate The date of the training.
     * @return A list of trainings that match the criteria.
     */
    List<Training> findTrainingByUsernameAndCriteria(String username, String trainingName, LocalDate trainingDate);

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
}
