import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserView, User } from '../models';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  roles = [{
    name: 'Admin',
    value: 'ADMIN'
  },
  {
    name: 'Client',
    value: 'CLIENT'
  }]


  _user: FormGroup;

  countries: string[][];

  @Output() loading = new EventEmitter<boolean>();

  @Output()
  user = new EventEmitter<User>();

  @Input() updateUser: Observable<User>;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this._user = this.fb.group({
      role: ['CLIENT', [Validators.required]],
      name: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      password: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      address: this.fb.group({
        streetAddress: ['', []],
        city: ['Madrid', []],
        country: ['Spain', []],
        zipCode: ['', []]
      })
    });
    if(this.updateUser){
      this.updateUser.subscribe(user => {
        this._user.setValue({
          role: user.role,
          name: user.name,
          lastName: user.lastName,
          password: user.password,
          email: user.email,
          address: user.address
        });
        this.loading.emit(false);
      }, error => {
        console.log(error);
      });
    }
  }

  getAllCountries(): void {
    console.log('hola');
    this.http.get<string[][]>('./countries.min.json').subscribe(success => {
      this.countries = success;
      console.log('hola');
      console.log(this.countries);
    }, error => {
      console.log(error);
    });
  }

  sendClient(): void { 
    this.user.emit(this._user.value);
  }

}
