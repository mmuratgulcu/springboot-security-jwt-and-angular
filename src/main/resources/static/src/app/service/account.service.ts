import { Injectable } from '@angular/core';
import { AuthenticationService } from '../security/authentication.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) { }

  register(registrationRequest:string){
    return this.http.post<any>(environment.API_BASE_PATH+'/register',registrationRequest);
  }
}
