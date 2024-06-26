//package com.epam.springcore.service;
//
//import com.epam.springcore.entity.*;
//import com.epam.springcore.repository.TrainingRepository;
//import com.epam.springcore.service.impl.TrainingServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TrainingServiceTest {
//    @InjectMocks
//    private TrainingServiceImpl trainingService;
//
//    @Mock
//    private TrainingRepository trainingRepository;
//
//    @Test
//    public void getAllTrainingsTest() {
//        List<Training> trainings = new ArrayList<>();
//        when(trainingRepository.findAll()).thenReturn(trainings);
//        System.out.println(trainings.size());
//
//        List<Training> allTrainings = trainingService.getAllTrainings();
//
//        assertEquals(trainings, allTrainings);
//    }
//
//    @Test
//    public void getTrainingByIdTest() {
//        Long trainingId = 1L;
//        Training training = new Training(1L,null, null, "asd", null, null, 60L);
//        when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(training));
//
//        Training foundTraining = trainingService.findTrainingById(trainingId);
//
//        assertEquals(training, foundTraining);
//    }
//
//    @Test
//    public void createTrainingTest() {
//        Trainer trainer = new Trainer(1L, new TrainingType(1l, "asd", new ArrayList<>(), new ArrayList<>()), new User(1L, "qqq", "www", "qqq.www", "qqq", true), new ArrayList<>(), new ArrayList<>());
//        Training training = new Training(1L, new Trainee(), trainer, "asd", new TrainingType(), LocalDate.of(1999, 12, 31), 20L);
//        //trainingService.createTraining(training);
//        //verify(trainingRepository, times(1)).save(training);
//
//    }
//
//    @Test
//    public void updateTrainingTest() {
//        Training training = new Training(1L, new Trainee(), new Trainer(), "asd", new TrainingType(), LocalDate.of(1999, 12, 31), 20L);
//        trainingService.updateTraining(training);
//        verify(trainingRepository, times(1)).save(training);
//    }
//
//    @Test
//    public void deleteTrainingTest() {
//        Long trainingId = 1L;
//        //trainingService.deleteTraining(trainingId);
//
//        //verify(trainingRepository, times(1)).deleteById(trainingId);
//    }
//}
