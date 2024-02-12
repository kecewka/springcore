package com.epam.springcore.mapper.Training;

import com.epam.springcore.dto.training.TrainerTrainingResponseDTO;
import com.epam.springcore.dto.training.TrainingPostDTO;
import com.epam.springcore.dto.training.TraineeTrainingResponseDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.TrainingTypeService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TrainerService.class, TraineeService.class, TrainingTypeService.class})
public abstract class TrainingMapper {

    @Autowired
    protected TraineeService traineeService;
    @Autowired
    protected TrainerService trainerService;
    @Autowired
    protected TrainingTypeService trainingTypeService;

    @Mapping(source = "traineeUsername", target = "trainee", qualifiedByName = "usernameToTrainee")
    @Mapping(source = "trainerUsername", target = "trainer", qualifiedByName = "usernameToTrainer")
    @Mapping(source = "trainingName", target = "trainingName")
    @Mapping(source = "trainingDate", target = "trainingDate")
    @Mapping(source = "trainingDuration", target = "trainingDuration")
    @Mapping(source = "trainingTypeName", target = "trainingType", qualifiedByName = "nameToTrainingType")
    public abstract Training dtoToTraining(TrainingPostDTO dto);

    @Mapping(source = "trainingName", target = "trainingName")
    @Mapping(source = "trainingDate", target = "trainingDate")
    @Mapping(source = "trainingType", target = "trainingType")
    @Mapping(source = "trainingDuration", target = "trainingDuration")
    @Mapping(source = "trainer", target = "trainerName", qualifiedByName = "trainerToName")
    public abstract TraineeTrainingResponseDTO trainingToDto(Training training);

    @Mapping(source = "trainingName", target = "trainingName")
    @Mapping(source = "trainingDate", target = "trainingDate")
    @Mapping(source = "trainingType", target = "trainingType")
    @Mapping(source = "trainingDuration", target = "trainingDuration")
    @Mapping(source = "trainee", target = "traineeName", qualifiedByName = "traineeToName")
    public abstract TrainerTrainingResponseDTO trainingToTrainerDto(Training training);

    @Named("traineeToName")
    protected String mapTraineeToString(Trainee trainee) {
        return trainee.getUser().getFirstName() + " " + trainee.getUser().getLastName();
    }
    @Named("trainerToName")
    protected String mapTrainerToString(Trainer trainer) {
        return trainer.getUser().getFirstName() + " " + trainer.getUser().getLastName();
    }

    @Named("usernameToTrainee")
    protected Trainee mapTraineeUsernameToTrainee(String username) {
        return traineeService.findTraineeByUsername(username);
    }

    @Named("usernameToTrainer")
    protected Trainer mapTrainerUsernameToTrainer(String username) {
        return trainerService.findTrainerByUsername(username);
    }

    @Named("nameToTrainingType")
    protected TrainingType mapNameToTrainingType(String name) {
        return trainingTypeService.findByName(name);
    }
}
