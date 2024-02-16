package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineeRepository;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.impl.TraineeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class TraineeServiceTest {

    @InjectMocks
    private TraineeServiceImpl traineeService;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTraineesTest() {
        List<Trainee> trainees = new ArrayList<>();
        when(traineeRepository.findAll()).thenReturn(trainees);

        List<Trainee> allTrainees = traineeService.getAllTrainees();

        verify(traineeRepository, times(1)).findAll();
        assertEquals(trainees, allTrainees);
    }

    @Test
    public void getTraineeByIdTest() {
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

        Trainee foundTrainee = traineeService.findTraineeById(traineeId);

        assertEquals(trainee, foundTrainee);
    }

    @Test
    public void getTraineeByUsernameTest() {
        String username = "john.doe";
        Trainee trainee = new Trainee();
        User user = new User(1L, "john", "doe", "john.doe", "asdqweasd1", true);
        trainee.setUser(user);
        Optional<Trainee> optional = Optional.of(trainee);
        when(userService.findUserByUsername(username)).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(optional);

        Trainee foundTrainee = traineeService.findTraineeByUsername(username);

        verify(userService, times(1)).findUserByUsername(username);
        verify(traineeRepository, times(1)).findByUserId(user.getId());
        assertEquals(trainee, foundTrainee);
    }

    @Test
    public void createTraineeTest() {
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", new User(1L, "asd", "asd", "asd.asd", "qweqweqweq", true), null, null);
        traineeService.createTrainee(trainee);
        verify(traineeRepository, times(1)).save(trainee);

    }

    @Test
    public void updateTraineeTest() {
        User user = new User(1L, "john", "doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", user, null, null);
        when(userService.findUserByUsername(trainee.getUser().getUsername())).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainee));
        traineeService.updateTrainee(trainee);
        verify(traineeRepository, times(1)).save(trainee);
    }

    @Test
    public void deleteTraineeTest() {
        Long traineeId = 1L;
        traineeService.deleteTrainee(traineeId);

        verify(traineeRepository, times(1)).deleteById(traineeId);
    }

    @Test
    public void deleteTraineeByUsernameTest() {
        String username = "john.doe";
        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "Str str. 31", user, new ArrayList<>(), new ArrayList<>());
        Optional<Trainee> optional = Optional.of(trainee);

        when(userService.findUserByUsername(username)).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(optional);

        traineeService.deleteTraineeByUsername(username);

        verify(userService, times(1)).findUserByUsername(username);
        verify(traineeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateTraineesTrainerListTest() {
        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", user, null, null);
        List<String> trainers = new ArrayList<>();
        Optional optional = Optional.of(trainee);
        when(userService.findUserByUsername(trainee.getUser().getUsername())).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainee));
        when(traineeRepository.findById(trainee.getId())).thenReturn(optional);
        traineeService.updateTraineesTrainerList(trainee.getUser().getUsername(), trainers);

        verify(traineeRepository, times(1)).save(trainee);
    }

    @Test
    public void changeTraineePasswordTest() {
        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", user, null, null);
        String password = "somepassasd";

        Optional optional = Optional.of(trainee);
        when(userService.findUserByUsername(trainee.getUser().getUsername())).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainee));
        when(traineeRepository.findById(trainee.getId())).thenReturn(optional);
        traineeService.changeTraineePassword(trainee.getId(), password);

        verify(traineeRepository, times(1)).save(trainee);
        assertEquals(password, trainee.getUser().getPassword());
    }

    @Test
    public void activateTraineeTest() {
        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", user, null, null);

        Optional optional = Optional.of(trainee);
        when(userService.findUserByUsername(trainee.getUser().getUsername())).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainee));
        when(traineeRepository.findById(trainee.getId())).thenReturn(optional);
        traineeService.activateTrainee(trainee.getId());

        verify(traineeRepository, times(1)).save(trainee);
        assertEquals(true, trainee.getUser().isActive());
    }

    @Test
    public void deactivateTraineeTest() {
        User user = new User(1L, "John", "Doe", "john.doe", "asdqweasd1", true);
        Trainee trainee = new Trainee(1L, LocalDate.of(1999, 12, 31), "asd str.31", user, null, null);

        Optional optional = Optional.of(trainee);
        when(userService.findUserByUsername(trainee.getUser().getUsername())).thenReturn(user);
        when(traineeRepository.findByUserId(user.getId())).thenReturn(Optional.of(trainee));
        when(traineeRepository.findById(trainee.getId())).thenReturn(optional);
        traineeService.deactivateTrainee(trainee.getId());

        verify(traineeRepository, times(1)).save(trainee);
        assertEquals(false, trainee.getUser().isActive());
    }
}
