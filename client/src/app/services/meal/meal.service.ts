import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../tokenStorage/token-storage.service';
import { MealInsert } from 'src/app/models/meal-insert';

const API_PATH = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})

export class MealService {

  constructor(private _http:HttpClient, 
    private _tokenStorage:TokenStorageService) { }
  
  
  private getHeadersWithToken() : HttpHeaders{
    let httpHeaders = new HttpHeaders({
      "Authorization" : "Bearer "+ this._tokenStorage.getToken(),
      'Content-Type': 'application/json'
    });
    return httpHeaders;
  }

  getMealByDate(userId: number, date:any){
    return this._http.get(API_PATH+"/api/meal/"+userId+"/list?date="+date,
                              {headers: this.getHeadersWithToken()});
  }

  getMealProductList(pathToMeal: String){
    return this._http.get(API_PATH + pathToMeal, {headers: this.getHeadersWithToken()})
  }

  createMeal(meal: MealInsert){
    return this._http.post(API_PATH + "/api/meal", meal, {headers: this.getHeadersWithToken()})
  }

  deleteMeal(id: Number){
    return this._http.delete(API_PATH + "/api/meal/" + id, {headers: this.getHeadersWithToken()});
  }


}
