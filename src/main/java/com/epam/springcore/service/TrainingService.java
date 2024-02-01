package com.epam.springcore.service;

import com.epam.springcore.entity.Training;

import java.util.List;

public interface TrainingService {

    /**
     * Retrieves a list of all trainings.
     *
     * @return A list of all trainings.
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves a training by its unique identifier.
     *
     * @param id The unique identifier of the training.
     * @return The training with the specified ID, or null if not found.
     */
    Training findTrainingById(Long id);

    /**
     * Creates a new training using the provided information.
     *
     * @param training The training to be created.
     */
    void createTraining(Training training);

    /**
     * Updates the information of an existing training.
     *
     * @param training The training with updated information.
     */
    void updateTraining(Training training);

    /**
     * Deletes a training by its unique identifier.
     *
     * @param id The unique identifier of the training to be deleted.
     */
    void deleteTraining(Long id);
}
