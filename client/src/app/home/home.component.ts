import { Component, OnInit } from '@angular/core';
import { DietService } from '../diet.service';
import {NgbModal, ModalDismissReasons, NgbTypeaheadSelectItemEvent} from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import {TokenStorageService} from "../token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dietService:DietService,
    private tokenStorageService:TokenStorageService,
    private router:Router) { }

  userInfoJson = {
    workouts: [],
    goals: [],
    measurements: []
  };

  userGoals = [];
  userDiet:Array<any>;
  currentDate = new Date()
  prevDate = new Date(this.currentDate);
  nextDate = new Date(this.currentDate);
  productWeightInMeal = 0;
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
  product:any = null;

  public productName:any;
  public productProteins:any;
  public productFats:any;
  public productCarbs:any;
  public productKcal:any;
  public productWeight:any;


  //meal

  public mealName = null;
  public mealDate:String;


  
  public object = {
      name: "",
      mealDate: "",
      mealsList: []
  }
  
  
  ngOnInit() {
    this.dietService.getUserGoals().subscribe(data =>{
      this.userInfoJson = data;
      this.userGoals = data.goals[data.goals.length-1];
    });
    
    this.dietService.getMeals().subscribe(data =>{
      this.userDiet = data;
      this.filterProductsWithCurrentDate(data);
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
      var products = object.filter((x) => { 
        return x.mealDate == this.currentDate
      });

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
    var proteins = 0;

    var i = 0;

    obj.map( x => x.product.proteins).forEach(element => {
      proteins += this.solveMacro(element,obj[i].productWeight);
      i++;
    });
    return Math.round(proteins*100)/100;

  }
  Carbs(obj, weigths){
    var carbs = 0;

    var i = 0;
    obj.map( x => x.product.carbs).forEach(element => {
      carbs += this.solveMacro(element,obj[i].productWeight);
      i++;
    });
    return Math.round(carbs*100)/100;
  }

  Fats(obj, weigths){
    var fats = 0;

    var i = 0;
    obj.map( x => x.product.fats).forEach(element => {
      fats += this.solveMacro(element,obj[i].productWeight);
      i++;
    });
    return Math.round(fats*100)/100;
  }
  Calories(obj, weigths){
    var calories = 0;

    var i = 0;
    obj.map( x => x.product.kcal).forEach(element => {
      calories += this.solveMacro(element,obj[i].productWeight);
      i++;
    });
    return Math.round(calories*100)/100;
  }



  closeAndSave(){
    if(this.object.name != "" && this.object.mealsList.length > 0){

      this.object.mealDate = this.formatDate(this.currentDate);
      this.userDiet.push(this.object);
      this.dietService.addMeals(this.object).subscribe(() => {
        this.object = {
          name: "",
          mealDate: "",
          mealsList: []
        }
        this.mealName = "";
       
      });
      this.closeModal('createmealmodal');
    }else{
      alert("Error!");
    }
 

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
        : this.productList.map(x => x.name)
        .filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)
        .slice(0, 10))
  )
  
  /**
   * 
   * @param element get $event when selected item in search product list 
   */
  selectItemFromResults(element){ 
    var productId = this.productList.map(x => x.name).indexOf(element.item);
    this.product  = this.productList[productId];
  }

  /**
   *  remove selected item
   */
  removeItemFromResults(){
    this.product = null;
    this.model = null;
    this.productWeightInMeal = null;
  }

  addProductToMeal(){

      if(this.product != null){
        if(!isNaN(this.productWeightInMeal)){
          this.object.mealsList.push(
            {
            product: this.product,
            productWeight: Number(this.productWeightInMeal)
            }
          );
          this.product = null;
          this.productWeightInMeal = null;
          this.model = '';
          this.closeModal('addproductmodal');
        }else{
          alert('Obiekt nie istnieje!');
        }

      }else{
        alert('Chose product!');
      }
   
  }




    deleteProduct(name){
      var id = this.object.mealsList.map(x => x.product.name).indexOf(name);
      console.log(this.object.mealsList.map(x => x.product.name).indexOf(name));
      this.object.mealsList.splice(id,1);
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

    signOut(){
      this.tokenStorageService.signOut();
      this.router.navigate(['']);
    }

    solveMacro(value,weightOfProduct){
      return value*weightOfProduct/100;
    }


    /**
     * open modal
     */
    openModal(element){
      document.getElementById(element).style.display = 'flex';
    }
    /**
     * close modal
     */
    closeModal(element){
      document.getElementById(element).style.display = 'none';
      
    }
      
    /**
     * discard changes
     */
    closeAndDiscard(){
      this.object = {
        name: "",
        mealDate: "",
        mealsList: []
      }
      this.mealName = "";
    }
  
}