import { Component, OnInit } from '@angular/core';
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
    this.dailyNewsService.getDailyNews().subscribe(
        dailyNews => {
          this.dailyNews = dailyNews;
          console.log(dailyNews);
        },
        error => {
          console.error("Ein Fehler ist aufgetreten", error);
        });
  }

}
