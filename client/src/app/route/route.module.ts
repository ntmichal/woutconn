import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { HomeComponent} from '../home/home.component';
import { WorkoutmanagerComponent } from '../workoutmanager/workoutmanager.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', component: LoginComponent },
  { path: 'workoutmanager', component: WorkoutmanagerComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})


export class RouteModule { }
