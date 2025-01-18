package com.github.tennyros.bootstrap.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.tennyros.bootstrap.models.User;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }
}
