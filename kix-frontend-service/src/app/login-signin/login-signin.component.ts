import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EdgeURL, AuthUtil, SnackBar } from '../utils';
import { User, UserView } from '../models';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login-signin',
  templateUrl: './login-signin.component.html',
  styleUrls: ['./login-signin.component.css']
})
export class LoginSigninComponent implements OnInit {

  _signin: FormGroup;

  @Output()
  signin: EventEmitter<boolean>;

  snackBar: SnackBar;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    snackBar: MatSnackBar
  ) { 
    this.signin = new EventEmitter();
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
    this._signin = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  letsSignin(): void{
    let auth: string = 'Basic ' + btoa(this._signin.get('email').value + ':' + this._signin.get('password').value);
    console.log(this._signin.get('email').value + ' ' + this._signin.get('password').value);
    this.http.get<UserView>(EdgeURL.concat('/login'), {headers: {Authorization: auth}}).subscribe(success => {
      AuthUtil.setSession(success);
      this.snackBar.openPanel('Loggin Successful!', 'SUCCESS');
      this.signin.emit(true);
    }, error => {
      let errorMessage: string;
      switch( error.status ){
        case 0: errorMessage = 'There is a problem with the server connection, please try again later'; break;
      case 401: errorMessage = 'Incorrect User/Password!'; break;
      case 403: errorMessage = 'Forbidden!'; break;
      case 500: errorMessage = 'Bad request, please, review your data!'; break;
       default: errorMessage = 'Unknown Error'; break;
      }
      this.snackBar.openPanel(errorMessage, 'ERROR');
      console.log(error);
    });
  }
}
