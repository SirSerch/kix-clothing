import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  @Output()
  signin = new EventEmitter<boolean>();

  @Output()
  signup = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  _signin($event){
    this.signin.emit($event);
  }

  _signup($event){
    this.signup.emit($event);
  }

}
