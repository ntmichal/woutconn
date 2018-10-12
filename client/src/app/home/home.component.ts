import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dietService:DietService) { }

  userGoals:Array<any>;

  ngOnInit() {
    this.dietService.getUserGoals().subscribe(data =>{
      this.userGoals = data;
      console.log(data);
    });
  }



}
