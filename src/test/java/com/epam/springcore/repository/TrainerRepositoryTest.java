package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository trainerRepository;
    @BeforeAll
    public void setUp() {
        trainerRepository.saveAllAndFlush(List.of(
                new Trainer(1L, null, new User(1L, "a", "b", "c", "d", true), null, null),
                new Trainer(2L, null, null, null, null),
                new Trainer(3L, null, null, null, null)
        ));
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
        assertThat(trainers).hasSize(1);
    }
}
