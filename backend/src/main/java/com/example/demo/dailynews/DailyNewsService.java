package com.example.demo.dailynews;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DailyNewsService {

    private final DailyNewsRepository dailyNewsRepostory;

    DailyNewsService(DailyNewsRepository dailyNewsRepostory) {
        this.dailyNewsRepostory = dailyNewsRepostory;
    }

    List<DailyNewsMessageEntity> loadDailyNews() {
        return dailyNewsRepostory.findAll();
    }
}
