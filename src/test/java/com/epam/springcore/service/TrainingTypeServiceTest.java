package com.epam.springcore.service;

import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.repository.TrainingTypeRepository;
import com.epam.springcore.service.impl.TrainingTypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TrainingTypeServiceTest {

    @InjectMocks
    private TrainingTypeServiceImpl trainingTypeService;

    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @Before
    public void inint() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTrainingTypesTest() {
        List<TrainingType> trainingTypes = new ArrayList<>();
        when(trainingTypeRepository.findAll()).thenReturn(trainingTypes);

        List<TrainingType> allTrainingTypes = trainingTypeService.findAllTrainingTypes();

        verify(trainingTypeRepository, times(1)).findAll();
        assertEquals(trainingTypes, allTrainingTypes);
    }

    @Test
    public void getTrainingTypeByNameTest() {
        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
        when(trainingTypeRepository.findByName(trainingType.getName())).thenReturn(Optional.of(trainingType));

        TrainingType found = trainingTypeService.findByName(trainingType.getName());

        verify(trainingTypeRepository, times(1)).findByName(trainingType.getName());
        assertEquals(trainingType, found);
    }

    @Test
    public void getTrainingTypeByIdTest() {
        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
        when(trainingTypeRepository.findById(trainingType.getId())).thenReturn(Optional.of(trainingType));

        TrainingType found = trainingTypeService.findById(trainingType.getId());

        verify(trainingTypeRepository, times(1)).findById(trainingType.getId());
        assertEquals(trainingType, found);
    }
}
