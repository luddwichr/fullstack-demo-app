package com.example.demo.user;

import com.example.demo.util.SystemTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static com.example.demo.util.TestResourceReader.loadResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRegistrationSystemTest extends SystemTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void register() throws Exception {
        mockMvc.perform(post("/registration")
                        .content(loadResource("requests/register.json"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andExpect(status().isCreated());

        var userCredentials = userRepository.findByUsername("testUser");
        assertThat(userCredentials.getUsername()).isEqualTo("testUser");
        assertThat(userCredentials.getPassword()).startsWith("$2a$15$");
        assertThat(userCredentials.getRoles()).containsExactly("USER");
    }

    @Test
    void missingCsrf() throws Exception {
        mockMvc.perform(post("/registration")
                        .content(loadResource("requests/register.json"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isForbidden());
    }
}
