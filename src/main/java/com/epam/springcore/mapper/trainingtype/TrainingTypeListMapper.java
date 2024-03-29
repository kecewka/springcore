package com.epam.springcore.mapper.trainingtype;

import com.epam.springcore.dto.trainingtype.TrainingTypeDTO;
import com.epam.springcore.entity.TrainingType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TrainingTypeMapper.class)
public interface TrainingTypeListMapper {
    List<TrainingTypeDTO> toDtoList(List<TrainingType> trainingTypes);
}
