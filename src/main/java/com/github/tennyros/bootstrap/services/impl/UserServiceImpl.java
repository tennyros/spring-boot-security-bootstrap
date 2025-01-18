package com.github.tennyros.bootstrap.services.impl;

import com.github.tennyros.bootstrap.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.tennyros.bootstrap.repositories.UserRepository;
import com.github.tennyros.bootstrap.models.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> "ROLE_ADMIN".equals(role.getRoleName()));
        user.setAdmin(isAdmin);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> "ROLE_ADMIN".equals(role.getRoleName()));
        user.setAdmin(isAdmin);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (id == 1) {
            log.error("You are trying to delete a user with id {} (super administrator)!", id);
            throw new UnsupportedOperationException("You can not delete super administrator!");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAllUsersWithRole();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
