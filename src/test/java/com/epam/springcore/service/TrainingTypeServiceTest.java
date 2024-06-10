//package com.epam.springcore.service;
//
//import com.epam.springcore.entity.TrainingType;
//import com.epam.springcore.repository.TrainingTypeRepository;
//import com.epam.springcore.service.impl.TrainingTypeServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TrainingTypeServiceTest {
//
//    @InjectMocks
//    private TrainingTypeServiceImpl trainingTypeService;
//
//    @Mock
//    private TrainingTypeRepository trainingTypeRepository;
//
//    @Test
//    public void getAllTrainingTypesTest() {
//        List<TrainingType> trainingTypes = new ArrayList<>();
//        when(trainingTypeRepository.findAll()).thenReturn(trainingTypes);
//
//        List<TrainingType> allTrainingTypes = trainingTypeService.findAllTrainingTypes();
//
//
//        verify(trainingTypeRepository, times(1)).findAll();
//        assertEquals(trainingTypes, allTrainingTypes);
//    }
//
//    @Test
//    public void getTrainingTypeByNameTest() {
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        when(trainingTypeRepository.findByName(trainingType.getName())).thenReturn(Optional.of(trainingType));
//
//        TrainingType found = trainingTypeService.findByName(trainingType.getName());
//
//        verify(trainingTypeRepository, times(1)).findByName(trainingType.getName());
//        assertEquals(trainingType, found);
//    }
//
//    @Test
//    public void getTrainingTypeByIdTest() {
//        TrainingType trainingType = new TrainingType(1L, "name", new ArrayList<>(), new ArrayList<>());
//        when(trainingTypeRepository.findById(trainingType.getId())).thenReturn(Optional.of(trainingType));
//
//        TrainingType found = trainingTypeService.findById(trainingType.getId());
//
//        verify(trainingTypeRepository, times(1)).findById(trainingType.getId());
//        assertEquals(trainingType, found);
//    }
//}
