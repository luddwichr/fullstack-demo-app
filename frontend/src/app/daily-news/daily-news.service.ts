import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DailyNewsMessage } from './daily-news-message';

@Injectable({
  providedIn: 'root'
})
export class DailyNewsService {

  constructor(private readonly http: HttpClient) {
  }

  fetchDailyNews(): Observable<DailyNewsMessage[]> {
    return this.http.get<DailyNewsMessage[]>("/api/daily-news");
  }
  
}
