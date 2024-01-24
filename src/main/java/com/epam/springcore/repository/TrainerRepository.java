package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "select * from trainers t " +
            "join users u on t.user_id = u.id " +
            "where u.is_active = true " +
            "and t.id not in (select trainer_id from trainee_trainer)", nativeQuery = true)
    List<Trainer> findAllActiveTrainersWithoutTrainees();
}