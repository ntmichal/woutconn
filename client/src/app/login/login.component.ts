import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth-service.service';
import { TokenStorageService } from '../services/tokenStorage/token-storage.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {


  constructor(
    private router:Router,
    private authService:AuthService,
     private tokenStorage:TokenStorageService,
    private formBuilder:FormBuilder
    ){ }

  loginForm!:FormGroup;

   ngOnInit() {
      this.loginForm = this.formBuilder.group({
        login: ['', [Validators.required]],
        password: ['', [Validators.required]]
      });
   }
  



  errorMessages = {
    login: null,
    email: null,
    password: null
  }

  success = false;
  swapFormBoolean = false;
  swapForm = () => {
    this.swapFormBoolean = !this.swapFormBoolean;
  };
 
  authenticate(){
    const loginForm = this.loginForm.value;
    this.authService.login(loginForm.login, loginForm.password).subscribe(data =>{
      this.tokenStorage.saveToken(JSON.stringify(data));
      this.loginForm.reset();
      this.router.navigate(['main']);
    },err =>{
      console.log(err.error);
    });
  }



}
