package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    /**
     * Retrieves a trainer based on their user ID
     *
     * @param id The unique identifier of the user
     * @return An {@code Optional} containing the trainee if found, or an empty {@code Optional} otherwise.
     * */
    Optional<Trainer> findByUserId(Long id);

    /**
     * Retrieves a list of active trainers who do not have any assigned trainee.
     * The SQL query joins the 'trainers' and 'users' tables, selecting specific columns for the Trainer and User entities.
     * It filters results to include only active users and trainers who are not assigned to any trainees.
     * @return A list of active trainers without assigned trainees.
     */
    @Query(value = "SELECT t.id AS trainer_id, t.specialization, t.user_id AS trainer_user_id, " +
            "u.id AS user_id, u.first_name, u.is_active, u.last_name, u.password, u.username " +
            "FROM trainers t " +
            "JOIN users u ON t.user_id = u.id " +
            "WHERE u.is_active = true " +
            "AND t.id NOT IN (SELECT trainer_id FROM trainee_trainer)", nativeQuery = true)
    List<Trainer> findAllActiveTrainersWithoutTrainees();
}