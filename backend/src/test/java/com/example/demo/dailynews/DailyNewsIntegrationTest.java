package com.example.demo.dailynews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("h2")
@AutoConfigureMockMvc
public class DailyNewsIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DailyNewsRepository dailyNewsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        final var expectedTitle = "some title";
        final var expectedMessage = "some message";
        dailyNewsRepository.save(new DailyNewsMessageEntity(expectedTitle, expectedMessage));

        String jsonContent = mockMvc.perform(get("/daily-news"))
            .andReturn().getResponse().getContentAsString();
        final var responseContent = objectMapper.readValue(jsonContent,
            new TypeReference<List<DailyNewsMessageResponse>>() {
            });

        assertThat(responseContent).hasSize(1);
        final var firstNews = responseContent.get(0);
        assertThat(firstNews.title()).isEqualTo(expectedTitle);
        assertThat(firstNews.message()).isEqualTo(expectedMessage);
    }
}
