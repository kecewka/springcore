package com.epam.springcore.mapper.trainingtype;

import com.epam.springcore.dto.trainingtype.TrainingTypeDTO;
import com.epam.springcore.entity.TrainingType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrainingTypeMapper {
    TrainingTypeMapper INSTANCE = Mappers.getMapper(TrainingTypeMapper.class);

    TrainingTypeDTO toDto(TrainingType trainingType);
}
