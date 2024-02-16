package com.epam.springcore.mapper.training;

import com.epam.springcore.dto.training.TraineeTrainingResponseDTO;
import com.epam.springcore.dto.training.TrainerTrainingResponseDTO;
import com.epam.springcore.entity.Training;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {TrainingMapper.class})
public interface TrainingListMapper {
    List<TraineeTrainingResponseDTO> trainingListToDto(List<Training> trainings);
    List<TrainerTrainingResponseDTO> trainingListToTrainerDto(List<Training> trainings);
}
