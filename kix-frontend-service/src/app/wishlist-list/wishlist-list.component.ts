import { Component, OnInit, ViewChild } from '@angular/core';
import { WishListView, ProductView } from '../models';
import { MatTableDataSource } from '@angular/material/table';
import { SnackBar, EdgeURL, AuthUtil } from '../utils';
import { MatSort } from '@angular/material/sort';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IndexProductDialogComponent } from '../index-product-dialog/index-product-dialog.component';
import { ProductDeleteComponent } from '../product-delete/product-delete.component';

@Component({
  selector: 'app-wishlist-list',
  templateUrl: './wishlist-list.component.html',
  styleUrls: ['./wishlist-list.component.css']
})
export class WishlistListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'created', 'products', 'actions'];

  wishLists: WishListView[] = [];

  dataSource = new MatTableDataSource();

  isLoading = false;
  isErrorLoading = false;
  isEmpty = false;

  title = 'Why not you create some?';
  subtitle = 'You don\'t have any wish list created yet';

  snackBar: SnackBar;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
    snackBar: MatSnackBar
  ) {
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
    this.isErrorLoading = !(this.isLoading = true);
    this.http.get<WishListView[]>(EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-lists`)).subscribe(
      wishlists => {
        console.log(wishlists);
        this.isLoading = false;
        this.isEmpty = wishlists.length === 0;
        this.wishLists = wishlists;
        this.dataSource = new MatTableDataSource(wishlists);
      },
      error => {
        this.isLoading = !(this.isErrorLoading = true);
        this.snackBar.openPanel(`Can't obtain all yours wish-lists from database`, 'ERROR');
        console.log(error);
      }
    );
    this.dataSource.sort = this.sort;
  }

  deleteWishList(wishList: WishListView, tableIndex: number): void {
    let url = `/users/${AuthUtil.getId()}/wish-lists/${wishList.id}`;
    this.http.delete(EdgeURL.concat(url)).subscribe(success => {
        this.wishLists.splice(tableIndex, 1);
        this.dataSource.data = this.wishLists;
        this.snackBar.openPanel('Wish List deleted correctly!', 'Success');
    }, error => {
      console.log(error);
      this.snackBar.openPanel('An error occurred while deleting the wish list', 'ERROR');
    })
  }


}
