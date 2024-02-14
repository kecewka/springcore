package com.epam.springcore.service;

import com.epam.springcore.entity.*;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.Impl.TrainingServiceImpl;
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


public class TrainingServiceTest {
    @InjectMocks
    private TrainingServiceImpl trainingService;

    @Mock
    private TrainingRepository trainingRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTrainingsTest() {
        List<Training> trainings = new ArrayList<>();
        when(trainingRepository.findAll()).thenReturn(trainings);
        System.out.println(trainings.size());

        List<Training> allTrainings = trainingService.getAllTrainings();

        assertEquals(trainings, allTrainings);
    }

    @Test
    public void getTrainingByIdTest() {
        Long trainingId = 1L;
        Training training = new Training();
        when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(training));

        Training foundTraining = trainingService.findTrainingById(trainingId);

        assertEquals(training, foundTraining);
    }

    @Test
    public void createTrainingTest() {
        Training training = new Training(1L, new Trainee(), new Trainer(), "asd", new TrainingType(), LocalDate.of(1999, 12, 31), 20L);
        trainingService.createTraining(training);
        verify(trainingRepository, times(1)).save(training);

    }

    @Test
    public void updateTrainingTest() {
        Training training = new Training(1L, new Trainee(), new Trainer(), "asd", new TrainingType(), LocalDate.of(1999, 12, 31), 20L);
        trainingService.updateTraining(training);
        verify(trainingRepository, times(1)).save(training);
    }

    @Test
    public void deleteTrainingTest() {
        Long trainingId = 1L;
        trainingService.deleteTraining(trainingId);

        verify(trainingRepository, times(1)).deleteById(trainingId);
    }
}
