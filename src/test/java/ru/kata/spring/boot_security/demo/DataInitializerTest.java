package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kata.spring.boot_security.demo.configs.DataInitializer;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import static org.mockito.Mockito.when;

class DataInitializerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_createsRolesAndAdminOnce() throws Exception {
        when(roleRepository.findByRoleName("ADMIN")).thenReturn(null);
        when(roleRepository.findByRoleName("USER")).thenReturn(null);
    }
}
