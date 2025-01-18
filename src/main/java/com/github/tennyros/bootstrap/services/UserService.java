package com.github.tennyros.bootstrap.services;

import com.github.tennyros.bootstrap.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
}
