package com.example.demo.dailynews;

import com.example.demo.util.SystemTest;
import org.junit.jupiter.api.Test;

import static com.example.demo.util.JsonContentResultMatcher.jsonContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DailyNewsControllerSystemTest extends SystemTest {

    @Test
    void getDailyNews() throws Exception {
        mockMvc.perform(get("/daily-news"))
                .andExpect(status().isOk())
                .andExpect(jsonContent().matchesFile("responses/daily-news.json"));
    }
}
