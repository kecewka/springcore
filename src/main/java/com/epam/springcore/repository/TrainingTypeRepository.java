package com.epam.springcore.repository;

import com.epam.springcore.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
    /**
     * Retrieves a training type based on their name
     *
     * @param name The name of the Training type
     * @return An {@code Optional} containing the training type if found, or an empty {@code Optional} otherwise.
     * */
    Optional<TrainingType> findByName(String name);
}
