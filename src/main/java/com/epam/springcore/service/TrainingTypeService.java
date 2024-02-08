package com.epam.springcore.service;

import com.epam.springcore.entity.TrainingType;

import java.util.List;

public interface TrainingTypeService {

    /**
     * Retrieves a list of all training types.
     *
     * @return A list of all training types.
     */
    public List<TrainingType> findAllTrainingTypes();

    /**
     * Retrieves a training type by their name.
     *
     * @param name The name of the training type.
     * @return The Training type with the specified name, or null if not found.
     */
    public TrainingType findByName(String name);
}
