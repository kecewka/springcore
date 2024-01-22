package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    // Calls database to get a list of all Records in trainees table
    List<Trainee> findAll();

    // Calls database to get a single Record in trainees table which matches id
    Optional<Trainee> findById(Long id);

    Optional<Trainee> findByUserId(Long id);



}
