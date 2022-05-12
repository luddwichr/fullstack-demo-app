package com.example.demo.dailynews;

import java.util.Optional;
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

    public DailyNewsMessageEntity createDailyNews(
        DailyNewsMessageResponse dailyNewsMessageResponse) {
        final var entity = new DailyNewsMessageEntity(dailyNewsMessageResponse.title(),
            dailyNewsMessageResponse.message());
        System.out.println(dailyNewsMessageResponse.title());
        System.out.println(dailyNewsMessageResponse.message());
        return dailyNewsRepostory.save(entity);
    }

    public Optional<DailyNewsMessageEntity> getDailyNews(long id) {
        return dailyNewsRepostory.findById(id);
    }
}
