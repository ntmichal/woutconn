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

  closeResult:string;
  username:string;
  password:string;
  email:string;

  buttonValue = "Register";
  loginForm = false;
  registerForm = true;

  registerData = {
    login: '',
    password: '',
    confirmpassword: '',
    email: ''
  };
  login(){
    this.authService.authenticate(this.username,this.password).subscribe(data =>{
      this.tokenStorage.saveToken(data.token);
      this.username = '';
      this.password = '';
      this.router.navigate(['home']);
    });
  }

  register(){
    console.log(this.registerData);
      if(this.registerData.password == this.registerData.confirmpassword){
        this.authService.register(
          this.registerData.login, 
          this.registerData.password, 
          this.registerData.email
          ).subscribe(data =>{
          console.log("registered");
        });
      }
     this.username = null;
     this.password = null; 
  }
   ngOnInit() {
   }
   makeAction(){
    this.registerForm = !this.registerForm;
    this.loginForm = !this.loginForm;
    if(this.buttonValue == "Register"){
      this.buttonValue = "Login";
    }else{
      this.buttonValue = "Register";
    }
 
   }

}
