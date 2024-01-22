//package com.epam.springcore.service;
//
//import com.epam.springcore.entity.Trainer;
//import com.epam.springcore.entity.TrainingType;
//import com.epam.springcore.entity.User;
//import com.epam.springcore.repository.TrainerRepository;
//import com.epam.springcore.service.Impl.TrainerServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class TrainerServiceTest {
//
//    @InjectMocks
//    private TrainerServiceImpl trainerService;
//
//    @Mock
//    private TrainerRepository trainerRepository;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void getAllTrainersTest() {
//        List<Trainer> trainers = new ArrayList<>();
//        when(trainerRepository.findAll()).thenReturn(trainers);
//        System.out.println(trainers.size());
//
//        List<Trainer> allTrainers = trainerService.getAllTrainers();
//
//        assertEquals(trainers, allTrainers);
//    }
//
//    @Test
//    public void getTrainerByIdTest() {
//        Long trainerId = 1L;
//        Trainer trainer = new Trainer();
//        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));
//
//        Trainer foundTrainer = trainerService.findTrainerById(trainerId);
//
//        assertEquals(trainer, foundTrainer);
//    }
//
//    @Test
//    public void createTrainerTest() {
//        Trainer trainer = new Trainer(1L, new TrainingType(1L, "asd", null, null), new User(1L, "asd", "asd", "asd.asd", "asd", true), null, null);
//        //trainerService.createTrainer(trainer);
//        //verify(trainerRepository, times(1)).createTrainer(trainer);
//
//    }
//
//    @Test
//    public void updateTrainerTest() {
//        Trainer trainer = new Trainer(1L, new TrainingType(1L, "asd", null, null), new User(1L, "asd", "asd", "asd.asd", "asd", true), null, null);
//        //trainerService.updateTrainer(trainer);
//        //verify(trainerRepository, times(1)).updateTrainer(trainer);
//    }
//
//    @Test
//    public void deleteTrainerTest() {
//        Long trainerId = 1L;
//        trainerService.deleteTrainer(trainerId);
//
//        verify(trainerRepository, times(1)).deleteTrainer(trainerId);
//    }
//}
