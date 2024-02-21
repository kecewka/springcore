package com.epam.springcore.repository;

import com.epam.springcore.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrainingRepositoryTest {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TraineeRepository traineeRepository;

    @BeforeAll
    public void setUp() {
        traineeRepository.saveAllAndFlush(List.of(
                new Trainee(1L, LocalDate.of(1999, 12, 31), "asd", new User(1L, "a", "b", "c", "d", true), null, null)
        ));

        trainingRepository.saveAllAndFlush(List.of(
                new Training(2L,
                        new Trainee(1L, LocalDate.of(1999, 12, 31), "asd", new User(1L, "a", "b", "c", "d", true), null, null),
                        null,
                        "name2", null, LocalDate.of(2023, 11, 20), 20L),
                new Training(3L,
                        null,
                        null,
                        "name3", null, LocalDate.of(2023, 9, 20), 20L)

        ));

    }

    @Test
    public void findAllTest() {
        List<Training> trainings = trainingRepository.findAll();
        assertThat(trainings).hasSize(2);
    }

}
