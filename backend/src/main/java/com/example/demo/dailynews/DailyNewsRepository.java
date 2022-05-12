package com.example.demo.dailynews;

import org.springframework.data.jpa.repository.JpaRepository;

interface DailyNewsRepository extends JpaRepository<DailyNewsMessageEntity, Long> {

}