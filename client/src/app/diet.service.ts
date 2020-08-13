import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { TokenStorageService } from './token-storage.service';
import { Observable } from 'rxjs';
import { ThrowStmt } from '@angular/compiler';
import 'rxjs/add/operator/map';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class DietService {

  constructor(private _http:HttpClient,private tokenStorage:TokenStorageService) { }


  getToken(){
    var token = JSON.parse(this.tokenStorage.getToken()).access_token;
    return '?access_token='+ token;
  }
  getMeals():Observable<any>{
    const uri = 'http://localhost:8080/api/meal/' + this.getToken(); 
    return this._http.get(uri);
  }

  addMeals(obj){
    const uri = "http://localhost:8080/api/meal" + this.getToken(); 
    return this._http.post(uri,obj);
  }

  deleteMeal(mealid:number){
    const uri = "http://localhost:8080/api/meal/"+mealid + this.getToken(); ;
    return this._http.delete(uri);
  }

  getUserGoals():Observable<any>{
    const uri = "http://localhost:8080/api/userinfojson" + this.getToken(); ;
    return this._http.get(uri);
  }

  getProducts():Observable<any>{
    const uri = "http://localhost:8080/api/product/list" + this.getToken(); ;
    return this._http.get(uri);
  }

  saveProduct(object):Observable<any>{

    const uri = "http://localhost:8080/api/product/" + this.getToken(); 

    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json'
    });

    return this._http.post(uri, object,{observe: 'response'});
  }
  updateMeal(meal,id){
    const uri = "http://localhost:8080/api/meal/"+id + this.getToken(); ;
    return this._http.put(uri,meal);
  }

  insertMeal(object){
    const uri = "http://localhost:8080/api/meal" + this.getToken(); ;
    return this._http.post(uri,object);
  }
  
  getWorkouts():Observable<any>{
    const uri = "http://localhost:8080/training" + this.getToken(); ;
    return this._http.get(uri);
  }

}
