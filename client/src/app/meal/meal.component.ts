import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';

@Component({
  selector: 'app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit {

  constructor(private dietService:DietService) { }

  productList:Array<any>;
  chosenProducts:Array<any> = [];
  meal:Array<any>;

  mealname:String;
  mealdate:String;
  ngOnInit() {
    this.dietService.getProducts().subscribe(data =>{
      this.productList = data;
    });
  }
  add(product){
    this.chosenProducts.push(product);
  }
  addMeal(){
    //backEnd sets user id
    var meal = {
      name : this.mealname,
      date : this.mealdate
    }
    var products = this.chosenProducts;

    var object = {
      meal : meal,
      products : products
    }
    console.log(object);

    this.dietService.insertMeal(object).subscribe();
  }
}
