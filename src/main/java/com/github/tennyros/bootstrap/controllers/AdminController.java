package com.github.tennyros.bootstrap.controllers;

import com.github.tennyros.bootstrap.dtos.UserDto;
import com.github.tennyros.bootstrap.exceptions.UserNotFoundException;
import com.github.tennyros.bootstrap.mappers.UserMapper;
import com.github.tennyros.bootstrap.models.Role;
import com.github.tennyros.bootstrap.utils.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.github.tennyros.bootstrap.models.User;
import com.github.tennyros.bootstrap.services.RegistrationService;
import com.github.tennyros.bootstrap.services.RoleService;
import com.github.tennyros.bootstrap.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;
    private final RoleService roleService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    private static final String ROLES = "roles";
    private static final String USERS = "users";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private static final String ADMIN_PAGE = "/admin/admin";
    private static final String REDIRECT_ADMIN_PAGE = "redirect:/admin/admin";
    private static final String ERROR_MESSAGE = "errorMessage";

    @GetMapping(value = "/admin")
    public String adminFullInfo(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute(USERS, users);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute(ROLES, roleService.getAllRoles());
        return ADMIN_PAGE;
    }

    @PostMapping(value = "/new_user")
    public String registrationExecution(@Valid @ModelAttribute("userDto") UserDto userDto,
                                        BindingResult result, Model model) {
        userValidator.validate(userDto, result);
        if (result.hasErrors()) {
            model.addAttribute(ROLES, roleService.getAllRoles());
            model.addAttribute(USERS, userService.getAllUsers());
            model.addAttribute("activeTab", "new-user");
            return ADMIN_PAGE;
        }
        User user = userMapper.toEntity(userDto);
        Set<Role> selectedRoles = userDto.getRoles();
        user.setRoles(selectedRoles);
        registrationService.register(user);
        return REDIRECT_ADMIN_PAGE;
    }


    @PostMapping(value = "/update")
    public String updateUserExecution(@Valid @ModelAttribute("userDto") UserDto userDto,
                                      BindingResult result, Model model) {
        userValidator.validate(userDto, result);
        if (result.hasErrors()) {
            model.addAttribute(ROLES, roleService.getAllRoles());
            model.addAttribute(USERS, userService.getAllUsers());
            return ADMIN_PAGE;
        }
        User user = userMapper.toEntity(userDto);
        userService.updateUser(user);
        return REDIRECT_ADMIN_PAGE;
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@ModelAttribute("userDto") UserDto userDto, Principal principal, Model model) {
        User currentUser = userService.getUserByEmail(principal.getName())
                .orElseThrow(() -> {
                    log.error("Current user is not found for principal {}", principal.getName());
                    return new UserNotFoundException("Current user now found!");
                });
        Long userId = userDto.getId();
        User userToDelete = userService.getUserById(userId);
        if (userToDelete.getId() == 1) {
            model.addAttribute(ERROR_MESSAGE, "You cannot delete the super administrator!");
            model.addAttribute(USERS, userService.getAllUsers());
            model.addAttribute(ROLES, roleService.getAllRoles());
            return ADMIN_PAGE;
        }
        if (userToDelete.getRoles().stream().anyMatch(role ->
                role.getAuthority().equals(ADMIN_ROLE)) && currentUser.getId() != 1) {
            model.addAttribute(ERROR_MESSAGE, "Only super administrator can delete other administrators!");
            model.addAttribute(USERS, userService.getAllUsers());
            model.addAttribute(ROLES, roleService.getAllRoles());
            return ADMIN_PAGE;
        }
        try {
            userService.deleteUser(userId);
        } catch (UnsupportedOperationException e) {
            log.error("Error while deleting user with id: {}", userId, e);
            model.addAttribute(ERROR_MESSAGE, e.getMessage());
            return "error";
        }
        return REDIRECT_ADMIN_PAGE;
    }
}
