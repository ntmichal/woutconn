import { Component, OnInit } from '@angular/core';
import { MealService } from '../services/meal/meal.service';
import { ProductService } from '../services/product/product.service';
import { mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit {

  constructor(private _mealService:MealService ,private _productService:ProductService) { }


  meals!:any;
  tempUserId = 1;
  
  productsPaths:any = [];

  ngOnInit(): void {
    var toDayDate = (new Date()).toLocaleString("fr-CA").substr(0,10)

    this._mealService
      .getMealByDate(this.tempUserId, toDayDate)
      .subscribe(data =>{
      this.meals = data;
    })
    

  }

  loadProducts(i:number) {
       this._mealService
           .getMealProductList(this.meals[i]["path"]).subscribe(
             data =>{
               this.productsPaths = data;
             }, error =>{

             }, ()=>{
                this.productsPaths.forEach((element: string) => {
                  this.loadProduct(element);
                });
             }
           )
  }

  loadProduct(value: string){
    this._productService.getProductById(Number(value.substr(13,14)))
    .subscribe(data=>{
      //TODO
    })
  }

  deleteMeal(id :number){
    this._mealService.deleteMeal(id).subscribe(()=>{

    },error => {

    }, ()=>{
      var index = this.meals.map( (x : any) => x.id).indexOf(id);
      this.meals.splice(index,1);
    });

  }
}
