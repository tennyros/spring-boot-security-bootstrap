package com.github.tennyros.bootstrap.mappers;

import org.mapstruct.Mapper;
import com.github.tennyros.bootstrap.dtos.UserDto;
import com.github.tennyros.bootstrap.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toEntity(UserDto userDto);
}
