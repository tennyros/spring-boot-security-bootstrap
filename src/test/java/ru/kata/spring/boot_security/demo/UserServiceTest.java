//package ru.kata.spring.boot_security.demo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.repositories.UserRepository;
//import ru.kata.spring.boot_security.demo.services.impl.UserServiceImpl;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetUserByUsername_UserExists() {
//        String email = "admin@admin.ru";
//        User user = new User();
//        user.setEmail(email);
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        Optional<User> foundUser = userService.getUserByEmail(email);
//
//        assertTrue(foundUser.isPresent());
//        assertEquals(email, foundUser.get().getUsername());
//    }
//}
