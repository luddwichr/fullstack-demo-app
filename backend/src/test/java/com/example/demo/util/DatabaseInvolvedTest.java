package com.example.demo.util;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
public abstract class DatabaseInvolvedTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:13-alpine")
                    .withDatabaseName("demo")
                    .withPassword("inmemory")
                    .withUsername("inmemory");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        System.out.println(postgreSQLContainer.getJdbcUrl());
    }

}