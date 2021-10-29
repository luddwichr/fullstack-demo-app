import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Spectator, createComponentFactory } from '@ngneat/spectator';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

describe('HomeComponent tested with specator', () => {
  let spectator: Spectator<HomeComponent>;
  const createComponent = createComponentFactory(HomeComponent);

  beforeEach(() => spectator = createComponent());

  it('displays a welcome message', () => {
    expect(spectator.query('p')).toContainText('Welcome');
  });
});
