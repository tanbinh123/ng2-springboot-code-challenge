import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { Http, XHRBackend, RequestOptions } from '@angular/http';
import { CustomHttp } from './integration/custom-http.service';

@NgModule({
  imports: [
      BrowserModule,
      FormsModule
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
      }
  ]
})
export class AppModule { }
