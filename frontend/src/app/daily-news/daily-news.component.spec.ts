import { createComponentFactory, mockProvider, byText } from '@ngneat/spectator';
import { of } from 'rxjs';

import { DailyNewsComponent } from './daily-news.component';
import { DailyNewsService } from './daily-news.service';

describe('DailyNewsComponent', () => {
  const createComponent = createComponentFactory({
    component: DailyNewsComponent,
    providers: [mockProvider(DailyNewsService)]
  });

  it('displays fetched daily news', () => {
    const spectator = createComponent({detectChanges: false});
    const dailyNews = [{title: 'titleA', message: 'messageA'}, {title: 'titleB', message: 'messageB'}];
    spectator.inject(DailyNewsService).fetchDailyNews.and.returnValue(of(dailyNews));

    spectator.detectChanges();

    for(const entry of dailyNews) {
      expect(spectator.query(byText(entry.title))).toExist();
      expect(spectator.query(byText(entry.message))).toExist();
    }
  });
});
