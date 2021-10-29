package com.example.demo.dailynews;

import org.springframework.data.repository.Repository;

import java.util.List;

interface DailyNewsRepository extends Repository<DailyNewsMessageEntity, Long> {
    List<DailyNewsMessageEntity> findAll();
}