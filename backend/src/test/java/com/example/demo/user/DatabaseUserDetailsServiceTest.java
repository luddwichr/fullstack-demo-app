package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseUserDetailsServiceTest {

    @InjectMocks
    private DatabaseUserDetailsService databaseUserDetailsService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDetailsMapper userDetailsMapper;

    @Test
    void loadUserByUsername() {
        var userCredentials = mock(UserCredentials.class);
        when(userRepository.findByUsername("testUser")).thenReturn(userCredentials);
        var mappedUserDetails = mock(UserDetails.class);
        when(userDetailsMapper.toUserDetails(userCredentials)).thenReturn(mappedUserDetails);

        var userDetails = databaseUserDetailsService.loadUserByUsername("testUser");

        assertThat(userDetails).isSameAs(mappedUserDetails);
    }
}