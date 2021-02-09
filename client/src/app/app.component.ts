import { Component } from '@angular/core';
import { DietNavigationComponent } from './diet-navigation/diet-navigation.component';
import { MealComponent } from './meal/meal.component';
import { LoginComponent } from './login/login.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'diet-app';
}
