import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token-storage.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router:Router,private authService:AuthService
     ,private tokenStorage:TokenStorageService
    ){ }

  username:string;
  password:string;
  email:string;
  login(){
    this.authService.authenticate(this.username,this.password).subscribe(data =>{
      this.tokenStorage.saveToken(data.token);
      this.router.navigate(['home']);
    });
  }

  register(){
    this.authService.register(this.username, this.password, this.email).subscribe(data =>{
      console.log("registered");
    });
  }
   ngOnInit() {
   }

}
