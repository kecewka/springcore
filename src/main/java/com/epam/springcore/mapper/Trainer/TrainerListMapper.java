package com.epam.springcore.mapper.Trainer;

import com.epam.springcore.dto.trainee.TraineeUpdateTrainerListDTO;
import com.epam.springcore.dto.trainer.TraineesTrainerResponseDTO;
import com.epam.springcore.entity.Trainer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TrainerMapper.class})
public interface TrainerListMapper {
    List<TraineesTrainerResponseDTO> trainerToTraineesResponseDTOList(List<Trainer> trainers);
}

