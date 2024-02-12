package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    /**
     * Retrieves a trainee based on their user ID
     *
     * @param id The unique identifier of the user
     * @return An {@code Optional} containing the trainee if found, or an empty {@code Optional} otherwise.
     * */
    Optional<Trainee> findByUserId(Long id);

    @Query(value = "select trainer_id from trainee_trainers where trainee_id = ?", nativeQuery = true)
    List<Trainer> findTrainersById(@Param("id") Long id);

}
