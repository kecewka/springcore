package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TraineeRepositoryTest {
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    TestEntityManager entityManager;

    @BeforeAll
    public void setUp() {
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer(1L, null, new User(5L, "tr", "tr", "tr.tr", "tr", true), new ArrayList<>(), new ArrayList<>()));
        traineeRepository.saveAllAndFlush(List.of(
                new Trainee(1L, LocalDate.of(1999, 12, 31), "asd", new User(1L, "a", "b", "c", "d", true), null, null),
                new Trainee(2L, LocalDate.of(1999, 11, 29), "ddd", null, null, null),
                new Trainee(3L, LocalDate.of(1999, 10, 31), "aqqq", null, null, null),
                new Trainee(4L, LocalDate.of(1999, 9, 15), "address", new User(3L, "a", "b", "qqq", "d", true), null, trainers)
        ));
    }

    @Test
    public void findByUserIdTest() {
        Optional<Trainee> optional = traineeRepository.findByUserId(1L);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd", new User(3L, "a", "b", "c", "d", true), null, null);
        User user = new User(3L, "a", "b", "c", "d", true);
        assertThat(optional.get().getUser().equals(user));
        assertThat(optional.get().equals(trainee));
    }

    @Test
    public void findTrainersByTrainerIdTest() {
        List<Long> list = traineeRepository.findTrainersByTraineeId(4L);
        List<Long> newList = new ArrayList<>();
        newList.add(1L);
        assertThat(list).isEqualTo(newList);
    }
}