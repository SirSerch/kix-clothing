import { Component, OnInit } from '@angular/core';
import { User, ProductView } from '../models';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EdgeURL, SnackBar } from '../utils';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  _user: User;
  userId: string;

  user?: Subject<User> = new Subject<User>();

  loading: boolean;
  errorLoading: boolean;

  loadingTitle: string = 'Gettin user';

  snackBar: SnackBar

  constructor(
    private http: HttpClient,
    private routerParam: ActivatedRoute,
    private router: Router,
    snackBar: MatSnackBar
  ) { 
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
    this.routerParam.paramMap.subscribe(params => {
      this.errorLoading = !(this.loading = true);
      this.http.get<User>(EdgeURL.concat(`/users/${params.get('id')}`)).subscribe(product => {
        this.userId = params.get('id');
        this._user = product;
        this.user.next(product);
      },
      error => {
        this.loading = !(this.errorLoading = true);
        this.snackBar.openPanel(`Can't obtain the user whith id: ${params.get('id')}`, 'ERROR');
        this.router.navigate(['/dashboard/users']);
      });
    });
  }

  updateUser(user: User): void{
    this.loadingTitle = 'Updating user';
    this.errorLoading = !(this.loading = true);
    this.http.put(EdgeURL.concat(`/users/${this.userId}`), user).subscribe(success => {
      this.snackBar.openPanel(`User with id: ${this.userId} UPDATED correctly!`, 'SUCCESS');
      this.router.navigateByUrl('/dashboard/users');
    }, error => {
      this.loading = !(this.errorLoading = true);
      this.snackBar.openPanel(`ERROR trying to update user with id: ${this.userId}`, 'ERROR');
    });
  }

}
