package com.example.demo.dailynews;

import com.example.demo.util.JpaRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DailyNewsRepositoryTest extends JpaRepositoryTest {

    @Autowired
    private DailyNewsRepository dailyNewsRepository;

    @Test
    void findAll() {
        var dailyNews = dailyNewsRepository.findAll();

        assertThat(dailyNews).usingRecursiveFieldByFieldElementComparator().containsExactly(
                new DailyNewsMessageEntity(1L, "BREAKING", "this morning, sun rose two minutes earlier than expected!"),
                new DailyNewsMessageEntity(2L, "URGENT", "A bag of rice fell over in China! Wow.")
        );
    }
}