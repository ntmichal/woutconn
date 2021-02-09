import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../../models/product';
import { TokenStorageService } from '../tokenStorage/token-storage.service';

const API_PATH = "http://localhost:8080/api/product";

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  
  constructor(private _http: HttpClient, private _tokenStorage:TokenStorageService) { }

  getProductById(productId: Number){
      return this._http.get(API_PATH+"/"+productId);
  }

  getProductsByName(name:String){
      return this._http.get(API_PATH+"?name="+name);
  }

  createProduct(product:Product){
      let httpHeaders = new HttpHeaders({
        "Authorization" : "Bearer "+ this._tokenStorage.getToken(),
        'Content-Type': 'application/json'
      });

      return this._http.post(API_PATH, product, {headers: httpHeaders});
  }

  deleteProduct(id: Number){
    let httpHeaders = new HttpHeaders({
      "Authorization" : "Bearer "+ this._tokenStorage.getToken(),
      'Content-Type': 'application/json'
    });

    return this._http.delete(API_PATH+"/"+id, {headers: httpHeaders});
  }

  updateProduct(id: Number, product:Product){
    let httpHeaders = new HttpHeaders({
      "Authorization" : "Bearer "+ this._tokenStorage.getToken(),
      'Content-Type': 'application/json'
    });

    return this._http.put(API_PATH+"/"+id, product, {headers: httpHeaders});
  }
}
