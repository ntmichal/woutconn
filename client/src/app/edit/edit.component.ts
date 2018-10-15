import { Component, OnInit } from '@angular/core';
import {Globals} from '../globals';
import { DietService} from '../diet.service';
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  constructor(private globals:Globals, private dietService:DietService) { }

  productList:Array<any>;

  ngOnInit() {

    this.dietService.getProducts().subscribe(data =>{
      this.productList = data;
    })
  }
  updateMeal(prod){
    this.dietService.updateMeal(prod, prod.meal.id).subscribe();

  }
  addProduct(prod){
    this.globals.usermeal.products.push(prod);

  }
  deleteProductFromMeal(prod){


    const index = 
      this.globals.usermeal.products.map(
        element =>
        { return element.id }
        ).indexOf(prod.id);

        console.log(index);
      this.globals.usermeal.products.splice(index,1);  
      

    }

  
}
