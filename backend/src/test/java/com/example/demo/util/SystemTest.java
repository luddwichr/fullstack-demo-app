package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@IntegrationTest
@AutoConfigureMockMvc
public abstract class SystemTest extends DatabaseInvolvedTest {

    @Autowired
    protected MockMvc mockMvc;

    protected RequestPostProcessor testerUser() {
        return user("tester").password("testerPassword").roles("USER");
    }
}