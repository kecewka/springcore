package com.epam.springcore.mapper.trainee;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {TraineeMapper.class})
public interface TraineeListMapper {

}