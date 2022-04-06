package com.example.demo.user;

import com.example.demo.util.JpaRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends JpaRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        UserCredentials testUser = new UserCredentials("testuser", "password", Set.of("USER"));
        userRepository.save(testUser);

        UserCredentials userCredentials = userRepository.findByUsername("testuser");

        assertThat(userCredentials).usingRecursiveComparison().isEqualTo(testUser);
    }
}