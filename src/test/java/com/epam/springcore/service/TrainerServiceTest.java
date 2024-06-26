//package com.epam.springcore.service;
//
//import com.epam.springcore.entity.Trainer;
//import com.epam.springcore.entity.TrainingType;
//import com.epam.springcore.entity.User;
//import com.epam.springcore.repository.TrainerRepository;
//import com.epam.springcore.service.impl.TrainerServiceImpl;
//import io.jsonwebtoken.security.Password;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TrainerServiceTest {
//
//    @InjectMocks
//    private TrainerServiceImpl trainerService;
//
//    @Mock
//    private TrainerRepository trainerRepository;
//    @Mock
//    private UserService userService;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void getAllTrainersTest() {
//        List<Trainer> trainers = new ArrayList<>();
//        when(trainerRepository.findAll()).thenReturn(trainers);
//
//        List<Trainer> allTrainers = trainerService.getAllTrainers();
//
//        verify(trainerRepository, times(1)).findAll();
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
//    public void getTrainerByUsernameTest() {
//        String username = "john.doe";
//        User user = new User(1L, "john", "doe", "john.doe", "asdqweasd1", true);
//        TrainingType trainingType = new TrainingType(1L, "asd", new ArrayList<>(), new ArrayList<>());
//        Trainer trainer = new Trainer(1L, trainingType, user, new ArrayList<>(), new ArrayList<>());
//        Optional<Trainer> optional = Optional.of(trainer);
//        when(userService.findUserByUsername(username)).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(optional);
//
//        Trainer foundTrainer = trainerService.findTrainerByUsername(username);
//
//        verify(userService, times(1)).findUserByUsername(username);
//        verify(trainerRepository, times(1)).findByUserId(user.getId());
//        assertEquals(trainer, foundTrainer);
//    }
//
//    @Test
//    public void createTrainerTest() {
//        Trainer trainer = new Trainer(1L, new TrainingType(1L, "asd", null, null), new User(1L, "asd", "asd", "asd.asd", "asd", true), null, null);
//        trainerService.createTrainer(trainer);
//        verify(trainerRepository, times(1)).save(trainer);
//
//    }
//
//    @Test
//    public void updateTrainerTest() {
//        User user = new User(1L, "asd", "asd", "asd.asd", "asd", true);
//        Trainer trainer = new Trainer(1L, new TrainingType(1L, "asd", null, null), user, null, null);
//        when(userService.findUserByUsername(trainer.getUser().getUsername())).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainer));
//        trainerService.updateTrainer(trainer);
//        verify(trainerRepository, times(1)).save(trainer);
//    }
//
//    @Test
//    public void deleteTrainerTest() {
//        Long trainerId = 1L;
//        trainerService.deleteTrainer(trainerId);
//
//        verify(trainerRepository, times(1)).deleteById(trainerId);
//    }
//
//    @Test
//    public void deleteTrainerByUsernameTest() {
//        String username = "john.doe";
//        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        Trainer trainer = new Trainer(1L, trainingType, user, new ArrayList<>(), new ArrayList<>());
//        Optional<Trainer> optional = Optional.of(trainer);
//
//        when(userService.findUserByUsername(username)).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(optional);
//
//        trainerService.deleteTrainerByUsername(username);
//
//        verify(userService, times(1)).findUserByUsername(username);
//        verify(trainerRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void changeTrainerPasswordTest() {
//        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        Trainer trainer = new Trainer(1L, trainingType, user, new ArrayList<>(), new ArrayList<>());
//        Optional<Trainer> optional = Optional.of(trainer);
//        String password = "somepassasd";
//
//        when(userService.findUserByUsername(trainer.getUser().getUsername())).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainer));
//        when(trainerRepository.findById(trainer.getId())).thenReturn(optional);
//        trainerService.changeTrainerPassword(trainer.getId(), password);
//
//        verify(trainerRepository, times(1)).save(trainer);
//        assertEquals(password, trainer.getUser().getPassword());
//    }
//
//    @Test
//    public void activateTrainerTest() {
//        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        Trainer trainer = new Trainer(1L, trainingType, user, new ArrayList<>(), new ArrayList<>());
//        Optional<Trainer> optional = Optional.of(trainer);
//
//        when(userService.findUserByUsername(trainer.getUser().getUsername())).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainer));
//        when(trainerRepository.findById(trainer.getId())).thenReturn(optional);
//        trainerService.activateTrainer(trainer.getId());
//
//        verify(trainerRepository, times(1)).save(trainer);
//        assertEquals(true, trainer.getUser().isActive());
//    }
//
//    @Test
//    public void deactivateTrainerTest() {
//        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        Trainer trainer = new Trainer(1L, trainingType, user, new ArrayList<>(), new ArrayList<>());
//        Optional<Trainer> optional = Optional.of(trainer);
//
//        when(userService.findUserByUsername(trainer.getUser().getUsername())).thenReturn(user);
//        when(trainerRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainer));
//        when(trainerRepository.findById(trainer.getId())).thenReturn(optional);
//        trainerService.deactivateTrainer(trainer.getId());
//
//        verify(trainerRepository, times(1)).save(trainer);
//        assertEquals(false, trainer.getUser().isActive());
//    }
//
//    @Test
//    public void getNotAssignedAndActiveTrainersTest() {
//        List<Trainer> trainers = new ArrayList<>();
//        when(trainerRepository.findAllActiveTrainersWithoutTrainees()).thenReturn(trainers);
//
//        List<Trainer> checkList = trainerService.getNotAssignedAndActiveTrainers();
//        verify(trainerRepository, times(1)).findAllActiveTrainersWithoutTrainees();
//        assertEquals(trainers, checkList);
//    }
//}
