import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http:HttpClient) { }


    login(username:String, password:String):Observable<any>{
    var headers = {
      'Authorization': 'Basic ' + btoa('client:clientsecret'),
      'Content-Type': 'application/x-www-form-urlencoded',
    }
    var user = new HttpParams()
      .set('username', username.toString())
      .set('password', password.toString())
      .set('grant_type','password');

    return this.http.post('http://localhost:8080/oauth/token', user, {headers});
  }
}
