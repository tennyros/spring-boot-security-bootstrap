package ru.kata.spring.boot_security.demo.mappers;

import org.mapstruct.Mapper;
import ru.kata.spring.boot_security.demo.dtos.UserDto;
import ru.kata.spring.boot_security.demo.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toEntity(UserDto userDto);
}
