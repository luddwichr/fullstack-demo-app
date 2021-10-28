package com.example.demo.dailynews;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/daily-news")
class DailyNewsController {

    private final DailyNewsService dailyNewsService;

    DailyNewsController(DailyNewsService dailyNewsService) {
        this.dailyNewsService = dailyNewsService;
    }

    @GetMapping
    public List<DailyNewsMessageResponse> getDailyNews() {
        return mapToResponse(dailyNewsService.loadDailyNews());
    }

    private List<DailyNewsMessageResponse> mapToResponse(List<DailyNewsMessageEntity> loadDailyNews) {
        return loadDailyNews.stream()
                .map(entry -> new DailyNewsMessageResponse(entry.getTitle(), entry.getMessage()))
                .collect(Collectors.toList());
    }

}
