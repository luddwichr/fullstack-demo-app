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
class DailyNewsServiceTest {

    @InjectMocks
    private DailyNewsService dailyNewsService;
    @Mock
    private DailyNewsRepository dailyNewsRepository;

    @Test
    void loadDailyNews() {
        var expectedDailyNews = List.of(new DailyNewsMessageEntity("title", "message"));
        when(dailyNewsRepository.findAll()).thenReturn(expectedDailyNews);

        var dailyNews = dailyNewsService.loadDailyNews();

        assertThat(dailyNews).isSameAs(expectedDailyNews);
    }

}