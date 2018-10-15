import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';
import { Router } from '@angular/router';
import { Globals} from '../globals';
@Component({
  selector: 'app-diet',
  templateUrl: './diet.component.html',
  styleUrls: ['./diet.component.css']
})
export class DietComponent implements OnInit {

  constructor(private dietService:DietService, private router:Router, private globals:Globals) { }

  userMealList:Array<any>;
  
  ngOnInit() {
    this.dietService.getMeals().subscribe(data =>{
      this.userMealList = data;
      
    })
  }

  addMeal(){

  }

  edit(usermeal){
    this.globals.usermeal = usermeal;
    this.router.navigate(['edit']);

  }

}
