package com.epam.springcore.mapper.Trainee;

import com.epam.springcore.dto.trainee.*;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.mapper.Trainer.TrainerMapper;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserService.class, TrainerMapper.class, TrainerService.class})
public abstract class TraineeMapper {

    @Autowired
    protected UserService userService;
    @Autowired
    protected TrainerMapper trainerMapper;
    @Autowired
    protected TrainerService trainerService;

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "user.active", target = "active")
    @Mapping(source = "trainers", target = "trainers")
    public abstract TraineeResponseDTO traineeToResponseDTO(Trainee trainee);

    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    public abstract Trainee registrationDTOtoTrainee(TraineeRegistrationDTO traineeRegistrationDTO);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.password", target = "password")
    public abstract UsernameAndPasswordDTO registrationResponse(Trainee trainee);

    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "active", target = "user.active")
    public abstract Trainee updateDTOtoTrainee(TraineeUpdateRequestDTO traineeUpdateRequestDTO);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "user.active", target = "active")
    @Mapping(source = "trainers", target = "trainers")
    public abstract TraineeUpdateResponseDTO traineeToUpdateResponseDTO(Trainee trainee);



}
