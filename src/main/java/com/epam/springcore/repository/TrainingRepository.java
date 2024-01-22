package com.epam.springcore.repository;

import com.epam.springcore.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    // Calls database to get a list of all Records in trainings table
    List<Training> findAll();

    // Calls database to get a single Record in trainings table which matches id
    Optional<Training> findById(Long id);

}