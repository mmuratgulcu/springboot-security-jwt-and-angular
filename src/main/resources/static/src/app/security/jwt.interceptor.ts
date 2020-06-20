import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor() {}
//Bütün isteklerde araya girecek. Eğer localStorage'de currentUser varsa requestin üzerine token ekler.
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if(currentUser && currentUser.token){
      request = request.clone({
        setHeaders:{
          Authorization:`Bearer ${currentUser.token}`
        }
      });
    }
    return next.handle(request);
  }
}
