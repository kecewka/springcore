package com.epam.springcore.mapper.User;

import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UsernameAndPasswordDTO toDto(User user);
}
