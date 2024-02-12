package com.epam.springcore.mapper.Trainer;

import com.epam.springcore.dto.trainee.TrainersTraineeDTO;
import com.epam.springcore.dto.trainer.*;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.mapper.Trainee.TraineeMapper;
import com.epam.springcore.service.TrainingTypeService;
import com.epam.springcore.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserService.class, TrainingTypeService.class})
public abstract class TrainerMapper {
    @Autowired
    protected UserService userService;

    @Autowired
    protected TrainingTypeService trainingTypeService;

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "mapSpecializationToLong")
    public abstract TraineesTrainerResponseDTO trainerToTraineesResponseDTO(Trainer trainer);

    @Named("mapSpecializationToLong")
    protected Long mapSpecializationToLong(TrainingType specialization) {
        return specialization.getId(); // assuming getId() returns the Long ID of TrainingType
    }

    @Named("LongToSpecialization")
    protected TrainingType mapLongToSpecialization(Long id) {
        return trainingTypeService.findById(id);
    }

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "mapSpecializationToLong")
    @Mapping(source = "user.active", target = "active")
    @Mapping(source = "trainees", target = "trainees")
    public abstract TrainerResponseDTO trainerToResponseDTO(Trainer trainer);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    public abstract TrainersTraineeDTO traineesToTrainersTraineeDTO(Trainee trainee);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "mapSpecializationToLong")
    public abstract NotAssignedTrainerResponseDTO trainerToNotAssigned(Trainer trainer);

    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "LongToSpecialization")
    public abstract Trainer registrationDTOtoTrainer(TrainerRegistrationDTO trainerRegistrationDTO);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.password", target = "password")
    public abstract UsernameAndPasswordDTO registrationResponse(Trainer trainer);

    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "LongToSpecialization")
    @Mapping(source = "active", target = "user.active")
    public abstract Trainer updateDTOtoTrainer(TrainerUpdateRequestDTO trainerUpdateRequestDTO);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "mapSpecializationToLong")
    @Mapping(source = "user.active", target = "active")
    @Mapping(source = "trainees", target = "trainees")
    public abstract TrainerUpdateResponseDTO trainerToUpdateResponseDTO(Trainer trainer);


}

