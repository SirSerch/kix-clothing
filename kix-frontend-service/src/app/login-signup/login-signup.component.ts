import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBar, EdgeURL, AuthUtil } from '../utils';
import { User, Role, UserView } from '../models';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login-signup.component.html',
  styleUrls: ['./login-signup.component.css']
})
export class LoginSignupComponent implements OnInit {

  _signup: FormGroup;

  @Output()
  signup: EventEmitter<boolean> = new EventEmitter();

  snackBar: SnackBar;

  isValidEmail: boolean;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    snackBar: MatSnackBar
  ) { 
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
    this._signup = this.fb.group({
      name: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_.+]+@[a-zA-Z0-9_.+]+\.[a-z.]+')]],
      password: ['', [Validators.required]]
    });
  }

  letsSignup(): void{
    let user: User = this._signup.value;
    user.role = Role.CLIENT;
    this.http.post<UserView>(EdgeURL.concat('/user'), user).subscribe(success => {
      AuthUtil.setSession(success);
      this.snackBar.openPanel('User register correctly!', 'SUCCESS');
      this.signup.emit(true);
    }, error => {
      let errorMessage: string;
      switch( error.status ){
        case 0: errorMessage = 'There is a problem with the server connection, please try again later'; break;
      case 401: errorMessage = 'Unauthorized!'; break;
      case 403: errorMessage = 'Forbidden!'; break;
      case 500: errorMessage = 'There was an error while trying to create your user, please revise and try again later'; break;
       default: errorMessage = 'Unknown Error'; break;
      }
      this.snackBar.openPanel(errorMessage, 'ERROR');
      console.log(error);
    });
  }
}
