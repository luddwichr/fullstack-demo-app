package com.example.demo.dailynews;

import javax.persistence.*;

@Entity(name = "daily_news_message")
@SequenceGenerator(name = "daily_news_message_id_seq", allocationSize = 1)
public class DailyNewsMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_news_message_id_seq")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String message;

    DailyNewsMessageEntity() {
        // required by JPA
    }

    DailyNewsMessageEntity(String title, String message) {
        this.title = title;
        this.message = message;
    }

    // for testing only!
    DailyNewsMessageEntity(Long id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }
}
