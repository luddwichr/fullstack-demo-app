package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegistrationControllerTest {

    @InjectMocks
    private UserRegistrationController userRegistrationController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void register() {
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        var expectedUserCredentials = new UserCredentials("username", "encodedPassword", Set.of("USER"));

        userRegistrationController.register(new UserCredentialsDto("username", "password"));

        verify(userRepository).save(refEq(expectedUserCredentials));
    }
}