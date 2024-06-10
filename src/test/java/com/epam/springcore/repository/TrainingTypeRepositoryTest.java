//package com.epam.springcore.repository;
//
//import com.epam.springcore.entity.TrainingType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TrainingTypeRepositoryTest {
//    @Autowired
//    TrainingTypeRepository trainingTypeRepository;
//
//    @BeforeAll
//    public void setUp() {
//        trainingTypeRepository.saveAllAndFlush(List.of(
//                new TrainingType(1L, "name", null, null)
//        ));
//    }
//
//    @Test
//    public void findByNameTest() {
//        Optional<TrainingType> found = trainingTypeRepository.findByName("name");
//        TrainingType trainingType = new TrainingType(1L, "name", null, null);
//        assertThat(found.get()).isEqualTo(trainingType);
//    }
//}
