import { createHttpFactory, HttpMethod, SpectatorHttp } from '@ngneat/spectator';
import { DailyNewsService } from './daily-news.service';

describe('DailyNewsService', () => {
  let spectator: SpectatorHttp<DailyNewsService>;
  const createHttp = createHttpFactory(DailyNewsService);

  beforeEach(() => spectator = createHttp());

  it('fetches daily news from backend', () => {
    const expectedDailyNews = [{title: 'titleA', message: 'messageA'}, {title: 'titleB', message: 'messageB'}];

    spectator.service.fetchDailyNews().subscribe(dailyNews => expect(dailyNews).toEqual(expectedDailyNews));

    spectator.expectOne('/api/daily-news', HttpMethod.GET).flush(expectedDailyNews);
  });
});
