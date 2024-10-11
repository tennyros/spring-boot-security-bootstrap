package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        Optional<User> userByEmail = userService.getUserByEmail(userDto.getEmail());
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(userDto.getId())) {
            errors.rejectValue("email", "", "User with such email is already exists!");
        }

        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.userDto",
                    "Passwords do not match!");
        }
    }
}
