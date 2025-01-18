package com.github.tennyros.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.github.tennyros.bootstrap.configs.DataInitializer;
import com.github.tennyros.bootstrap.repositories.RoleRepository;
import com.github.tennyros.bootstrap.repositories.UserRepository;

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
