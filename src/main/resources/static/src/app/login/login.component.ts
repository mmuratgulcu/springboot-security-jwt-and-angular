import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../security/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms'
import { AccountService } from '../service/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  returnUrl: string;
  status:boolean=false;
  errorMsg="";

  constructor(private authenticationService: AuthenticationService,
    private accountService:AccountService,
    private route: ActivatedRoute,
    private router:Router,
    private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.authenticationService.logout();
    this.returnUrl = this.route.queryParams['returnUrl'] || '/pages/dashboard';

    this.loginForm = this.formBuilder.group({
      username:['',[Validators.required]],
      password:['',[Validators.required]]
    })
  }

  login(loginRequest:string) {
    this.authenticationService.login(loginRequest).subscribe(
      (data) => {
        this.router.navigate([this.returnUrl]);
      },
      (error) => {
        this.errorMsg = error;
        this.status=true;
      });
  }

  toRegister(){
    this.router.navigateByUrl('/register');
  }
}
