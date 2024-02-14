package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;
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

    /**
     * Retrieves a List of Trainers based on their trainee id
     * @param id id of a trainee
     * @return A {@code List} of Long containing Trainer id
     */

    @Query(value = "select trainer_id from trainee_trainer where trainee_id = ?", nativeQuery = true)
    List<Long> findTrainersByTraineeId(@Param("id") Long id);

}
