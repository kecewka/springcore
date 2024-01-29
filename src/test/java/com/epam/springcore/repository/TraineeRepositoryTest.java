package com.epam.springcore.repository;

import com.epam.springcore.config.TestConfig;
import com.epam.springcore.entity.Trainee;
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
public class TraineeRepositoryTest {
    @Autowired
    private TraineeRepository traineeRepository;

    @Before
    public void setUp() {
        traineeRepository.saveAllAndFlush(List.of(
                new Trainee(1L, LocalDate.of(1999, 12, 31), "asd",new User(1L, "a", "b", "c", "d", true), null, null),
                new Trainee(2L, LocalDate.of(1999, 11, 29), "ddd", null, null, null),
                new Trainee(3L, LocalDate.of(1999, 10, 31), "aqqq", null, null, null)
        ));
    }

    @Test
    public void findAllTest() {
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(3);
    }

    @Test
    public void findByIdTest() {
        Optional<Trainee> optional = traineeRepository.findById(1L);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd", null, null, null);
        assertThat(optional.get().equals(trainee));
    }

    @Test
    public void findByUserIdTest() {
        Optional<Trainee> optional = traineeRepository.findByUserId(1L);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd",new User(1L, "a", "b", "c", "d", true), null, null);
        User user = new User(1L, "a", "b", "c", "d", true);
        assertThat(optional.get().getUser().equals(user));
        assertThat(optional.get().equals(trainee));
    }
}