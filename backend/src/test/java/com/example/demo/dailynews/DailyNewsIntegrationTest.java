package com.example.demo.dailynews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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

    @DirtiesContext
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

    @DirtiesContext
    @Test
    void postAndGetSingle() throws Exception {
        final var expectedTitle = "someTitle";
        final var expectedMessage = "someMessage";
        var postResponse = mockMvc.perform(
                post("/daily-news")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(
                        new DailyNewsMessageResponse(expectedTitle, expectedMessage))))
            .andExpect(status().isCreated())
            .andReturn().getResponse();
        var singleNewsPath = postResponse.getHeader("location");

        assertThat(singleNewsPath).isNotNull();

        mockMvc.perform(get(singleNewsPath))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", is(expectedTitle)))
            .andExpect(jsonPath("$.message", is(expectedMessage)));
    }
}
