import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';


import { FormsModule } from '@angular/forms';
import {RouteModule} from './route/route.module';
import {Interceptor} from "./interceptor";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {DietService} from './diet.service';
import {TokenStorageService} from './token-storage.service';
import {AuthService} from './auth.service';


import { Globals } from './globals';

import { NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouteModule,
    FormsModule,
    NgbModule
  ],
  providers: [Globals, DietService, AuthService, TokenStorageService, TokenStorageService,
    {provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
