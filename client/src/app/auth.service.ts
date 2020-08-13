import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  register(username:String,password:String,email:String) : Observable<any>{
    const userObject = {
      username: username,
      password: password,
      email: email
    }
    const uri = 'http://localhost:8080/api/signup';
    return this.http.post(uri, userObject);
  }
}
