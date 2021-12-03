import { Component, OnInit } from '@angular/core';
import { GlobalExceptionHandler } from '../global-exception-handler';
import { DailyNewsMessage } from './daily-news-message';
import { DailyNewsService } from './daily-news.service';

@Component({
  selector: 'app-daily-news',
  templateUrl: './daily-news.component.html',
  styleUrls: ['./daily-news.component.css']
})
export class DailyNewsComponent implements OnInit {

  dailyNews: DailyNewsMessage[] = [];

  constructor(private readonly dailyNewsService: DailyNewsService) { }

  ngOnInit(): void {
    this.retrieveDailyNews();
  }

  retrieveDailyNews(): void {
    this.dailyNewsService
      .fetchDailyNews()
      .subscribe({next: dailyNews => this.dailyNews = dailyNews, error: GlobalExceptionHandler.handleException});
  }

}
