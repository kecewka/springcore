package com.epam.springcore.service;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.Impl.TraineeRepositoryImpl;
import com.epam.springcore.service.Impl.TraineeServiceImpl;
import com.epam.springcore.service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @InjectMocks
    private TraineeServiceImpl traineeService;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private TraineeRepositoryImpl traineeRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTraineesTest() {
        List<Trainee> trainees = new ArrayList<>();
        when(traineeRepository.findAll()).thenReturn(trainees);
        System.out.println(trainees.size());

        List<Trainee> allTrainees = traineeService.getAllTrainees();

        assertEquals(trainees, allTrainees);
    }

    @Test
    public void getTraineeByIdTest() {
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(traineeRepository.findById(traineeId)).thenReturn(trainee);

        Trainee foundTrainee = traineeService.findTraineeById(traineeId);

        assertEquals(trainee, foundTrainee);
    }

    @Test
    public void createTraineeTest() {
        Trainee trainee = new Trainee(1L, LocalDate.of(1999,12,31),"asd str.31", new User(1L, "asd", "asd", "asd.asd", "qweqweqweq", true), null, null);
        //traineeService.createTrainee(trainee);
        //verify(traineeRepository, times(1)).createTrainee(trainee);

    }

    @Test
    public void updateTraineeTest() {
        Trainee trainee = new Trainee(1L, LocalDate.of(1999,12,31),"asd str.31", null, null, null);
        //traineeService.updateTrainee(trainee);
        //verify(traineeRepository, times(1)).updateTrainee(trainee);
    }

    @Test
    public void deleteTraineeTest() {
        Long traineeId = 1L;
        traineeService.deleteTrainee(traineeId);

        verify(traineeRepository, times(1)).deleteTrainee(traineeId);
    }
}
