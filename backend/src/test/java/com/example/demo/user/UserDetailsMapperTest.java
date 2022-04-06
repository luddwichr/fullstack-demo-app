package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserDetailsMapperTest {
    @Test
    void toUserDetails() {
        var userDetailsMapper = new UserDetailsMapper();
        var userCredentials = new UserCredentials("username", "password", Set.of("USER"));
        var userDetails = userDetailsMapper.toUserDetails(userCredentials);
        assertThat(userDetails).isEqualTo(User.withUsername("username").password("password").roles(new String[]{"USER"}).build());
    }
}