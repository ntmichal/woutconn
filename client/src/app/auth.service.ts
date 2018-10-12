import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  authenticate(username:String,password:String):Observable<any>{
    const userObject = {
      username: username,
      password: password
    };
    const uri = 'http://localhost:8080/api/signin';
    return this.http.post(uri,userObject);
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
