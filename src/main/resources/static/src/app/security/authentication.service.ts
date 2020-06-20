import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient,private route:Router) { }

  login(loginRequest:string) {
    return this.http.post<any>(environment.API_BASE_PATH+'/singIn', loginRequest)
      .pipe(
        map(user => {
          if(user && user.token){
            localStorage.setItem('currentUser',JSON.stringify(user.token));
          }
          return user;
        }))
  }

  logout(){
    localStorage.removeItem('currentUser');
  }
}
