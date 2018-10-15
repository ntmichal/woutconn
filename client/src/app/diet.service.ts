import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ThrowStmt } from '@angular/compiler';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class DietService {

  constructor(private _http:HttpClient) { }

  getMeals():Observable<any>{
    const uri = 'http://localhost:8080/api/meal/';
    return this._http.get(uri);
  }

  addMeals(obj){
    const uri = "http://localhost:8080/api/meal"
    return this._http.post(uri,obj);
  }

  deleteMeal(mealid:number){
    const uri = "http://localhost:8080/api/meal/"+mealid;
    return this._http.delete(uri);
  }

  getUserGoals():Observable<any>{
    const uri = "http://localhost:8080/api/usergoals";
    return this._http.get(uri);
  }

  getProducts():Observable<any>{
    const uri = "http://localhost:8080/api/product/list";
    return this._http.get(uri);
  }

  updateMeal(meal,id){
    const uri = "http://localhost:8080/api/meal/"+id;
    return this._http.put(uri,meal);
  }

  insertMeal(object){
    const uri = "http://localhost:8080/api/meal";
    return this._http.post(uri,object);
  }
  
}
