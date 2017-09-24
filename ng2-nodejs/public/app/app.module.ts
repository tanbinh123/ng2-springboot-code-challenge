import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { Http, XHRBackend, RequestOptions, HttpModule } from '@angular/http';
import { CustomHttp } from './integration/custom-http.service';
import { ApiUtil } from './integration/api.util';
import { EmployeeService } from './service/employee.service';

@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      HttpModule
  ],
  declarations: [
      AppComponent,
      HomeComponent
  ],
  bootstrap: [
      AppComponent
  ],
  providers: [
      {
          provide: Http,
          useFactory: (
              backend: XHRBackend,
              options: RequestOptions,
          ) => {
              return new CustomHttp(
                  backend,
                  options
              );
          },
          deps: [XHRBackend, RequestOptions]
      },
      ApiUtil,
      EmployeeService
  ]
})
export class AppModule { }
