
import { Router, Routes } from '@angular/router';
import { createRoutingFactory } from '@ngneat/spectator';
import { MockComponent } from 'ng-mocks';
import { AppComponent } from './app.component';
import { DailyNewsComponent } from './daily-news/daily-news.component';
import { HomeComponent } from './home/home.component';

describe('AppComponent', () => {
  const dailyNewsComponentMock = MockComponent(DailyNewsComponent);
  const homeComponentMock = MockComponent(HomeComponent);
  const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch:'full'},
    {path: 'home', component: homeComponentMock},
    {path: 'daily-news', component: dailyNewsComponentMock},
  ];

  const createComponent = createRoutingFactory({
    component: AppComponent,
    declarations: [dailyNewsComponentMock, homeComponentMock],
    stubsEnabled: false,
    routes
  });

  it('routes to HomeComponent by default', async () => {
    const spectator = createComponent();

    await spectator.fixture.whenStable();

    expect(spectator.inject(Router).url).toBe('/home');
    expect(spectator.query(HomeComponent)).toExist();
  });

  it('navigates to DailyNewsComponent on link-click', async () => {
    const spectator = createComponent();

    await spectator.fixture.whenStable();
    
    expect(spectator.query(DailyNewsComponent)).not.toExist();

    spectator.click('a');

    await spectator.fixture.whenStable();

    expect(spectator.inject(Router ).url).toBe('/daily-news');
    expect(spectator.query(DailyNewsComponent)).toExist();
  });
});
