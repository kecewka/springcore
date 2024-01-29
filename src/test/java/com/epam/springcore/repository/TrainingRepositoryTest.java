package com.epam.springcore.repository;

import com.epam.springcore.config.TestConfig;
import com.epam.springcore.entity.*;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainingRepositoryTest {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TraineeRepository traineeRepository;

    @Before
    public void setUp() {
        traineeRepository.saveAllAndFlush(List.of(
                new Trainee(1L, LocalDate.of(1999, 12, 31), "asd",new User(1L, "a", "b", "c", "d", true), null, null)
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

    @Test
    public void findByIdTest() {
        Optional<Training> optional = trainingRepository.findById(2L);
        Training training = new Training(2L,
                null,
                null,
                "name2", null, LocalDate.of(2023, 11, 20), 20L);
        assertThat(optional.get().equals(training));
    }

    @Test
    @Transactional
    public void findByUsernameAndCriteriaTest() {
        List<Training> trainings = trainingRepository.findByUsernameAndCriteria(1L, "name2");
        assertThat(trainings).hasSize(1);
    }
}
