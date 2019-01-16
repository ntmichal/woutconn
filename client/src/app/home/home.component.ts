import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dietService:DietService,
    private modalService: NgbModal) { }

  userGoals:Array<any>;
  userDiet:Array<any>;
  userWorkouts:Array<any>;
  caloriesThisDay:Number = 0;
  currentDate = new Date()
  prevDate = new Date(this.currentDate);
  nextDate = new Date(this.currentDate);

  productList:Array<any>;  
  prevDateVal:any;
  nextDateVal:any;

  //counter for showing data
  mealCounter = 0

  day:any;
  month:any;
  year:any;

  public model: any;


  //product
  product = {
    name : "",
    barcode: "",
    proteins: 0,
    carbs: 0,
    fats: 0,  
    kcal: 0,
    volume: 0,
  }

  public productName:any;
  public productProteins:any;
  public productFats:any;
  public productCarbs:any;
  public productKcal:any;
  public productWeight:any;


  //meal

  public mealName:any;
  public mealDate:String;

  public object = {
    meal:  {
      id: null,
      name: "",
      mealDate: ""
      },
      products: []  
  }
  
  ngOnInit() {
    this.dietService.getUserGoals().subscribe(data =>{
      this.userGoals = data;

    });

    this.dietService.getMeals().subscribe(data =>{
      this.userDiet = data;
      console.log(data);
      this.filterProductsWithCurrentDate(data);
    });

    this.dietService.getWorkouts().subscribe(data =>{
      this.userWorkouts = data;

      console.log(data);
    });

    this.dietService.getProducts().subscribe(data =>{
      this.productList = data;
    });

    var cDate = new Date();
    this.day = cDate.getDate();
    this.month = cDate.getMonth();
    this.year = cDate.getFullYear();

    this.mealDate = this.formatDate(this.currentDate);

    //init date
    this.initDate();

    
  }

    range = {
      starts: 0,
      ends: 0
    }
    //date logic
    initDate(){
 
      this.prevDate.setDate(this.prevDate.getDate() -1);
      this.prevDate.toLocaleDateString();
  
      
      this.nextDate.setDate(this.nextDate.getDate() +1);
      this.nextDate.toLocaleDateString();
    }

    decreaseDate(){
      this.nextDate.setDate(this.nextDate.getDate() -1);
      this.currentDate = new Date(this.prevDate);
      this.prevDate.setDate(this.prevDate.getDate() -1);
  
    }
    increaseDate(){
      this.prevDate.setDate(this.prevDate.getDate() +1);
      this.currentDate = new Date(this.nextDate);
      this.nextDate.setDate(this.nextDate.getDate() +1);
    }
    /**
     * 
     * @param data returns formatted data yyyy-mm-dd
     */
    formatDate(data):string{

      var day = data.getDate();
      if(day >0 && day < 10){
        day = "0"+day;
      }
      var month = data.getMonth()+1;
      if(month >0 && month < 10){
        month = "0"+month;
      }

      return data.getFullYear()+"-"+month+"-"+day;
    }

    //map meal with current date
    filterProductsWithCurrentDate(object){
      var products = object.filter((x) => { return x.meal.mealDate == this.currentDate});


    }



  solveCalories(obj:Array<any>):Number{
    var objKcal = obj.map( x => x.kcal).reduce((x,y)=>{
      return x+y;
    });
    this.caloriesThisDay += objKcal;
    return objKcal;
  }
  calcDiffer():any{
    if(this.caloriesThisDay < this.userGoals[this.userGoals.length-1].zapotrzebowanie_kcal){
      return "Brakuje " +  (this.userGoals[this.userGoals.length-1].zapotrzebowanie_kcal-Number(this.caloriesThisDay)) + "Kcal";
    }else{
      return "Nadwyżka " +  Math.abs((this.userGoals[this.userGoals.length-1].zapotrzebowanie_kcal-Number(this.caloriesThisDay))) + "Kcal";
    }
  }
  BWT(obj):any{

    var B = obj.map( x => x.proteins).reduce((x,y)=>{
      return x+y;
    });
    var W = obj.map( x => x.carbs).reduce((x,y)=>{
      return x+y;
    });
    var T = obj.map( x => x.fats).reduce((x,y)=>{
      return x+y;
    });
    return "B:"+B+" W" + W+" T:"+T
  
  }

  Proteins(obj){
    return obj.map( x => x.proteins).reduce((x,y)=>{
      return x+y;
    });
  }
  Carbs(obj){
    return obj.map( x => x.carbs).reduce((x,y)=>{
      return x+y;
    });
  }

  Fats(obj){
    return obj.map( x => x.fats).reduce((x,y)=>{
      return x+y;
    });
  }
  Calories(obj){

    return obj.map(x => x.kcal).reduce( (x,y) =>{
      return x+y;
    });
  }

  //modal open
  
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }


  //modal logic

  closeAndDiscard(){
    this.object = {
      meal:  {
        id: null,
        name: "",
        mealDate: ""
        },
        products: []  
    }
    this.mealName = "";
  }

  closeAndSave(){


    this.object.meal.mealDate = this.formatDate(this.currentDate);
    

    this.userDiet.push(this.object);

    this.dietService.addMeals(this.object).subscribe(() => {
      this.object = {
        meal:  {
          id: null,
          name: "",
          mealDate: ""
          },
          products: []  
      }
      this.mealName = "";
     
    });

  }
  closeOwnProductWithoutSave(){
    this.productName = null;
    this.productProteins = null;
    this.productFats = null;
    this.productCarbs = null;
    this.productKcal = null;
    this.productWeight = null;
  }
  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 2 ? []
        : this.productList.map(x => x.name).filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )
 
    addProductToMeal(){
      var productId = this.productList.map(x => x.name).indexOf(this.model);
        this.object.products.push(this.productList[productId]);
    }

    addProduct(){

      var productTMP = {
        id : null,
        name : this.productName,
        barcode: "",
        proteins: this.productProteins,
        carbs: this.productCarbs,
        fats: this.productFats,  
        kcal: this.productKcal,
        volume: this.productWeight,
      }
     
      this.dietService.saveProduct(productTMP).subscribe(res => {

        productTMP.id = res.headers.get('id') 
        this.productList.push(productTMP);
      });
      
      this.object.products.push(productTMP);
    

    }



    zmienna:any = null;


    openTable(id){
      if(this.zmienna == id){
        this.zmienna = null;
      }else{
        this.zmienna = id;
      }

    }
    //open table with products of meal
    tableManagment(i){
      if(this.zmienna == i){
        return false;
      }
      return true;
    }

    
}