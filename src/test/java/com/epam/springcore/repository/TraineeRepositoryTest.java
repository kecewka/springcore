//package com.epam.springcore.repository;
//
//import com.epam.springcore.config.TestConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.epam.springcore.entity.Trainee;
//import com.epam.springcore.repository.TraineeRepository;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class TraineeRepositoryTest {
//
//
//    private TraineeRepository traineeRepository;
//
//    @Autowired
//    public void setRepo(TraineeRepository traineeRepository){
//        this.traineeRepository = traineeRepository;
//    }
//
//    @Test
//    public void testFindAll() {
//        // Call findAll() method
//        List<Trainee> trainees = traineeRepository.findAll();
//
//        // Assert the results
//        assertEquals(0, trainees.size());
//        // Add more assertions based on your implementation
//    }
//
//
//}
