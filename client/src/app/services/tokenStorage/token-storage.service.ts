import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public saveToken(token: string){
    window.sessionStorage.removeItem('token');
    window.sessionStorage.setItem('token',  token);
  }

  public removeToken(){
    window.sessionStorage.removeItem('token');
    window.sessionStorage.clear();
  }
  public getToken(){
    return JSON.parse(sessionStorage.getItem('token')!).access_token;
  }
}
