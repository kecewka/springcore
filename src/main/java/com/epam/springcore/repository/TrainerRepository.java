package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    // Calls database to get a list of all Records in trainers table
    List<Trainer> findAll();

    // Calls database to get a single Record in trainers table which matches id
    Optional<Trainer> findById(Long id);


}