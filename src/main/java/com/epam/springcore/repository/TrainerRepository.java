package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    // Calls database to get a list of all Records in trainers table
    List<Trainer> findAll();

    // Calls database to get a single Record in trainers table which matches id
    Optional<Trainer> findById(Long id);

    // Calls database to get a single Record in trainers table which matches user_id
    Optional<Trainer> findByUserId(Long id);

    // Calls database to get a list of records from trainers table
    // which are not included in trainee_trainer table and are active
    @Query(value = "SELECT t.id AS trainer_id, t.specialization, t.user_id AS trainer_user_id, " +
            "u.id AS user_id, u.first_name, u.is_active, u.last_name, u.password, u.username " +
            "FROM trainers t " +
            "JOIN users u ON t.user_id = u.id " +
            "WHERE u.is_active = true " +
            "AND t.id NOT IN (SELECT trainer_id FROM trainee_trainer)", nativeQuery = true)
    List<Trainer> findAllActiveTrainersWithoutTrainees();
}