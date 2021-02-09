import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DietNavigationComponent } from './diet-navigation.component';

describe('DietNavigationComponent', () => {
  let component: DietNavigationComponent;
  let fixture: ComponentFixture<DietNavigationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DietNavigationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DietNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
