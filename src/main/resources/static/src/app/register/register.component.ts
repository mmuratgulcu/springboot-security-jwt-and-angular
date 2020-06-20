import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccountService } from '../service/account.service';
import { Router } from '@angular/router';
import { __await } from 'tslib';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  status=false;

  constructor(private formBuilder: FormBuilder,
    private accountService: AccountService,
    private router:Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    })
  }

  register(registrationRequest: string) {
    this.accountService.register(registrationRequest).subscribe(
      (data) => {
        this.status=true;
        // this.router.navigateByUrl('login');
      },
      (error) => {
        console.log(error);
      });
  }

  toLogin(){
    this.router.navigateByUrl('login');
  }

}
