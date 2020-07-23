import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserView } from '../models';
import { HttpClient } from '@angular/common/http';
import { EdgeURL, SnackBar } from '../utils';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserDeleteComponent } from '../user-delete/user-delete.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'lastName', 'email', 'role', 'actions'];

  dataSource: MatTableDataSource<UserView> = new MatTableDataSource<UserView>();

  isLoading = false;
  isErrorLoading = false;
  isEmpty = false;

  users: UserView[] = [];

  snackBar: SnackBar;

  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
    snackBar: MatSnackBar
  ) { 
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
    this.isLoading = !(this.isErrorLoading = false);
    this.http.get<UserView[]>(EdgeURL.concat('/users')).subscribe(success => {
      this.isLoading = false;
      this.isEmpty = success.length === 0;
      this.dataSource = new MatTableDataSource(success);
      this.users = success;
    }, error => {
      this.isErrorLoading = !(this.isLoading = false);
      console.log(error);
    })
  }

  deleteUser(user: UserView, tableIndex: number): void {
    console.log(user);
    const dialogRef = this.dialog.open(UserDeleteComponent, {
      width: '400px',
      data: user
    });
    dialogRef.afterClosed().subscribe( productResult => {
      if (productResult !== undefined && productResult) {
        this.users.splice(tableIndex, 1);
        this.dataSource.data = this.users;
        this.snackBar.openPanel('User Delete Correctly!', 'Success');
      }
    });
  }

}
