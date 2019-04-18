import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token-storage.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


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

  closeResult:string;
  username:string;
  
  swapFormBoolean = false;
  swapForm = () => {
    this.swapFormBoolean = !this.swapFormBoolean;
  };
 
  authenticate(){
    const loginForm = this.loginForm.value;
    this.authService.authenticate(loginForm.login, loginForm.password).subscribe(res =>{
      this.tokenStorage.saveToken(res.token);
      this.loginForm.reset();
      this.router.navigate(['home']);
    },err =>{
      // this.serverMessages = err.error;
    });
  }

  register(){
    const registerForm = this.registerForm.value;
    if(registerForm.password == registerForm.confirmpassword){
      this.authService.register(
        registerForm.login, 
        registerForm.password, 
        registerForm.email
        ).subscribe(data =>{
        console.log("registered");
        this.registerForm.reset();
      });
    }
  }
   ngOnInit() {


      this.registerForm = this.formBuilder.group({
        login: ['',[Validators.required, Validators.minLength(5)]],
        password: ['',[Validators.required, Validators.minLength(8)]],
        confirmpassword:['', [Validators.required, Validators.minLength(8)]],
        email:['', [
          Validators.required,
          Validators.email
        ]]
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



}
