import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { HomeComponent} from '../home/home.component';
import { DietComponent } from '../diet/diet.component';
import { EditComponent } from '../edit/edit.component';
import { MealComponent } from '../meal/meal.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'diet',component: DietComponent},
  { path: 'meal', component: MealComponent},
  { path: 'edit', component: EditComponent},
  { path : '', component : LoginComponent},
  
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
