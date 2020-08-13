import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token-storage.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {ConfirmPasswordValidator} from '../confirm-password.validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  constructor(
    private router:Router,private authService:AuthService
     ,private tokenStorage:TokenStorageService
      ,private formBuilder:FormBuilder
    ){ }

/**
 * 
 */
    registerForm:FormGroup;
/**
 * 
 */
    loginForm:FormGroup;

  errorMessages = {
    login: null,
    email: null,
    password: null
  }

  closeResult:string;
  username:string;
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
      this.router.navigate(['home']);
    },err =>{
      console.log(err.error);
    });
  }

  register(){
    const registerForm = this.registerForm.value;
      this.authService.register(
        registerForm.login, 
        registerForm.password, 
        registerForm.email
        ).subscribe(data =>{
        this.success = true;
        this.registerForm.reset();
      }, error =>{
        this.success = false;
        const errorArray = error.error;
         if(errorArray["user"] !== undefined){
           console.log(errorArray.user);
          this.errorMessages.login = errorArray.user;
        }else{
          this.errorMessages.login = null;
        }
        if(errorArray["password"] !== undefined){
          this.errorMessages.password = errorArray.password;
        }else{
          this.errorMessages.password = null;
        }
        if(errorArray["email"] !== undefined){
          this.errorMessages.email = errorArray.email;
        }else{
          this.errorMessages.email = null;
        }
      });
  }
   ngOnInit() {


      this.registerForm = this.formBuilder.group({
        login: ['',[Validators.required, Validators.minLength(4)]],
        password: ['',[Validators.required, Validators.pattern(/^(?=.*[A-Z])(?=.*[!@#\$%\^&\*])(?=.{9,})/)]],
        confirmpassword:['', [Validators.required, Validators.pattern(/^(?=.*[A-Z])(?=.*[!@#\$%\^&\*])(?=.{9,})/)]],
        email:['', [
          Validators.required,
          Validators.email
        ]]
      },{
        validator: ConfirmPasswordValidator.MatchPassword
      });

      this.loginForm = this.formBuilder.group({
        login: ['', [Validators.required]],
        password: ['', [Validators.required]]
      });
   }


   get login(){
     return this.registerForm.get('login');
   }
   get password(){
    return this.registerForm.get('password');
  }
  get confirmpassword(){
    return this.registerForm.get('confirmpassword');
  }
  get email(){
    return this.registerForm.get('email');
  }

  serverError(object, dictKEY){
    this.registerForm.get(dictKEY).valueChanges.subscribe(() => {
      this.errorMessages[dictKEY] = null;
    });
    if(object != null){
      return true;
    }
    return null;
  }


}
