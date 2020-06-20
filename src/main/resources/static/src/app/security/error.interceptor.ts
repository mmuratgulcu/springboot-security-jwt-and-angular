import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { catchError } from 'rxjs/operators';

/**
 * Yapılan requeste dönen response'yi kontrol eder. Eğer tokenın süresi bitmişse 401 hatası döner.
 */

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status == 401) {
        this.authenticationService.logout();
        location.reload(true);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }))
  }
}



