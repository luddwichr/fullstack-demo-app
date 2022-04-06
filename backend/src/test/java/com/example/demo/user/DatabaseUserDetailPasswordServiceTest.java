package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseUserDetailPasswordServiceTest {

    @InjectMocks
    private DatabaseUserDetailPasswordService databaseUserDetailPasswordService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDetailsMapper userDetailsMapper;

    @Test
    void updatePassword() {
        var userCredentials = new UserCredentials();
        var user = User.withUsername("userName").password("oldPassword").roles().build();
        var mappedUpdatedUser = mock(UserDetails.class);
        when(userRepository.findByUsername("userName")).thenReturn(userCredentials);
        when(userDetailsMapper.toUserDetails(userCredentials)).thenReturn(mappedUpdatedUser);

        var updatedUser = databaseUserDetailPasswordService.updatePassword(user, "newPassword");

        assertThat(updatedUser).isSameAs(mappedUpdatedUser);
        assertThat(userCredentials.getPassword()).isEqualTo("newPassword");
    }

}