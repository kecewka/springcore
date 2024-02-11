package com.epam.springcore.mapper.Trainer;

import com.epam.springcore.dto.trainer.TraineesTrainerResponseDTO;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserService.class})
public abstract class TrainerMapper {
    @Autowired
    protected UserService userService;

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization", qualifiedByName = "mapSpecializationToLong")
    public abstract TraineesTrainerResponseDTO trainerToTraineesResponseDTO(Trainer trainer);

    @Named("mapSpecializationToLong")
    protected Long mapSpecializationToLong(TrainingType specialization) {
        return specialization.getId(); // assuming getId() returns the Long ID of TrainingType
    }


}
