package com.epam.springcore.repository;

import com.epam.springcore.config.TestConfig;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.User;
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
public class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired TraineeRepository traineeRepository;

    @Before
    public void setUp() {
        traineeRepository.saveAllAndFlush(List.of(
                new Trainee(1L, LocalDate.of(1999, 12, 31), "asd",new User(2L, "a", "b", "c", "d", true), null, null)
        ));
        trainerRepository.saveAllAndFlush(List.of(
                new Trainer(1L, null, new User(1L, "a", "b", "c", "d", true), null, null),
                new Trainer(2L, null, null, null, null),
                new Trainer(3L, null, null, null, null)
        ));
    }

    @Test
    public void findAllTest() {
        List<Trainer> traineeList = trainerRepository.findAll();
        assertThat(traineeList).hasSize(3);
    }

    @Test
    public void findByIdTest() {
        Optional<Trainer> optional = trainerRepository.findById(1L);
        Trainer trainer = new Trainer(1L, null, null, null, null);
        assertThat(optional.get().equals(trainer));
    }

    @Test
    public void findByUserIdTest() {
        Optional<Trainer> optional = trainerRepository.findByUserId(1L);
        Trainer trainer = new Trainer(1L, null, new User(1L, "a", "b", "c", "d", true), null, null);
        User user = new User(1L, "a", "b", "c", "d", true);
        assertThat(optional.get().getUser().equals(user));
        assertThat(optional.get().equals(trainer));
    }

    @Test
    public void findAllActiveTrainersWithoutTraineesTest() {
        List<Trainer> trainers = trainerRepository.findAllActiveTrainersWithoutTrainees();
        System.out.println(trainers.size());
    }
}
