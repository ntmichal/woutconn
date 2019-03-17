import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutmanagerComponent } from './workoutmanager.component';

describe('WorkoutmanagerComponent', () => {
  let component: WorkoutmanagerComponent;
  let fixture: ComponentFixture<WorkoutmanagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutmanagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutmanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
