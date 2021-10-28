import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DailyNewsComponent } from './daily-news/daily-news.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'daily-news', component: DailyNewsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
