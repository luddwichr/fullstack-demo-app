package com.example.demo.dailynews;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(DailyNewsController.BASE_PATH)
class DailyNewsController {

    protected static final String BASE_PATH = "/daily-news";

    private final DailyNewsService dailyNewsService;

    DailyNewsController(DailyNewsService dailyNewsService) {
        this.dailyNewsService = dailyNewsService;
    }

    @GetMapping
    public List<DailyNewsMessageResponse> getDailyNews() {
        return mapToResponse(dailyNewsService.loadDailyNews());
    }

    @PostMapping
    public ResponseEntity<DailyNewsMessageResponse> postDailyNews(@RequestBody
        DailyNewsMessageResponse dailyNewsMessageResponse) {
        var entity = dailyNewsService.createDailyNews(dailyNewsMessageResponse);
        final var uri = UriComponentsBuilder.newInstance().path(BASE_PATH + "/" + entity.getId())
            .build()
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<DailyNewsMessageResponse> getSingleDailyNews(@PathVariable long id) {
        var dailyNews = dailyNewsService.getDailyNews(id)
            .map(entity -> new DailyNewsMessageResponse(
                entity.getTitle(), entity.getMessage()));
        return ResponseEntity.of(dailyNews);
    }

    private List<DailyNewsMessageResponse> mapToResponse(
        List<DailyNewsMessageEntity> loadDailyNews) {
        return loadDailyNews.stream()
            .map(entry -> new DailyNewsMessageResponse(entry.getTitle(), entry.getMessage()))
            .collect(Collectors.toList());
    }

}
