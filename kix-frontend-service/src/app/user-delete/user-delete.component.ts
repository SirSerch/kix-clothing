import { Component, OnInit, Inject } from '@angular/core';
import { UserView, ProductView } from '../models';
import { SnackBar, EdgeURL } from '../utils';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  user: UserView;
  snackBar: SnackBar;
  deleting: boolean = false;
  errorDeleting: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<UserDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) user: UserView,
    private http: HttpClient,
    snackBar: MatSnackBar
  ) {
    this.user = user;
    this.snackBar = new SnackBar(snackBar);
   }

  ngOnInit(): void {
  }

  deleteUser(): void {
    this.errorDeleting = !(this.deleting = true);
    this.http.delete(EdgeURL.concat('/users/' + this.user.id)).subscribe(success => {
      this.snackBar.openPanel('Product ' + this.user.id + ' Correctly!', 'SUCCESS');
      this.dialogRef.close(true);
    },
    error => {
      this.deleting = !(this.errorDeleting = true);
      this.snackBar.openPanel('An error occured while trying to delete product: ' + this.user.id, 'ERROR');
      this.dialogRef.close(false);
    });
  }

}
