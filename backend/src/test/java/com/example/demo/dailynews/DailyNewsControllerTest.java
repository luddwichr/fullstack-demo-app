package com.example.demo.dailynews;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DailyNewsControllerTest {

    @InjectMocks
    private DailyNewsController dailyNewsController;
    @Mock
    private DailyNewsService dailyNewsService;

    @Test
    void getDailyNews() {
        var dailyNewsEntities = List.of(
                new DailyNewsMessageEntity("titleA", "messageA"),
                new DailyNewsMessageEntity("titleB", "messageB")
        );
        when(dailyNewsService.loadDailyNews()).thenReturn(dailyNewsEntities);

        var dailyNews = dailyNewsController.getDailyNews();

        assertThat(dailyNews).containsExactly(
                new DailyNewsMessageResponse("titleA", "messageA"),
                new DailyNewsMessageResponse("titleB", "messageB")
        );
    }
}