package com.epam.springcore.repository;

import com.epam.springcore.config.TestConfig;
import com.epam.springcore.entity.TrainingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainingTypeRepositoryTest {
    @Autowired
    TrainingTypeRepository trainingTypeRepository;

    @Before
    public void setUp() {
        trainingTypeRepository.saveAllAndFlush(List.of(
                new TrainingType(1L, "name", null, null)
        ));
    }

    @Test
    public void findByNameTest() {
        Optional<TrainingType> found = trainingTypeRepository.findByName("name");
        TrainingType trainingType = new TrainingType(1L, "name", null, null);
        assertThat(found.equals(trainingType));
    }
}
