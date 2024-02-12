package com.epam.springcore.mapper.Trainee;

import com.epam.springcore.dto.trainee.TrainersTraineeDTO;
import com.epam.springcore.entity.Trainee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {TraineeMapper.class})
public interface TraineeListMapper {

}
