import { Component, OnInit } from '@angular/core';
import { SnackBar, EdgeURL } from '../utils';
import { UserView } from '../models';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  snackBar: SnackBar;

  creating: boolean;
  creatingError: boolean;

  constructor(
    private http: HttpClient,
    private router: Router,
    snackBar: MatSnackBar
  ) {
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
  }

  createUser(user: UserView): void {
    this.creatingError = !(this.creating = true);
    this.http.post(EdgeURL.concat('/user'), user).subscribe(success => {
      this.creating = false;
      this.snackBar.openPanel(`Client ${user.name + ' ' + user.lastName} Has been correctly created`, 'SUCCESS');
      this.router.navigate(['dashboard/users']);
    }, error => {
      this.creating = !(this.creatingError = true);
      this.snackBar.openPanel(`Error while creating user`, 'ERROR');
    })
  }

}
