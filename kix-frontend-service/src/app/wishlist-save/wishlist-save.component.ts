import { Component, OnInit, Inject } from '@angular/core';
import { UserView, ProductView, WishListView } from '../models';
import { SnackBar, EdgeURL, AuthUtil } from '../utils';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserDeleteComponent } from '../user-delete/user-delete.component';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WishlistListComponent } from '../wishlist-list/wishlist-list.component';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-wishlist-save',
  templateUrl: './wishlist-save.component.html',
  styleUrls: ['./wishlist-save.component.css']
})
export class WishlistSaveComponent implements OnInit {
  products: ProductView[];
  snackBar: SnackBar;
  saving: boolean = false;
  errorSaving: boolean = false;
  isInvalid: boolean = true;
  wishLists: WishListView[] = [];
  saveForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<UserDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) products: ProductView[],
    private http: HttpClient,
    snackBar: MatSnackBar,
    private fb: FormBuilder,
    private favorites: StorageService
  ) {
    this.products = products;
    this.snackBar = new SnackBar(snackBar);
   }

  ngOnInit(): void {
    this.getWishLists();
    this.saveForm = this.fb.group({
      name: ['', []],
      id: ['', []]
    });
    this.saveForm.valueChanges.subscribe(values => {
      if(values.name.length > 0 || values.id > 0){
        this.isInvalid = false;
      } else {
        this.isInvalid = true;
      }
    });

  }
  getWishLists(): void {
    if (AuthUtil.isAuth()){
    this.http.get<WishListView[]>(EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-lists`)).subscribe(
      wishlists => {
        this.wishLists = wishlists;
      },
      error => {
        console.log(error);
        this.snackBar.openPanel('Can\'t retrieve all your wish-lists', 'ERROR');
      }
    );
    }
  }

  saveWishList(): void{
    if (AuthUtil.isAuth()){
      if (this.saveForm.get('id').value > 0){
        this.updateList();
      } else {
        this.createNewList();
      }
    }
  }

  private createNewList(): void {
    console.log(this.createWishList());
    console.log(AuthUtil.getId());
    const url = EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-list`);
    console.log(url);
    this.http.post(url, this.createWishList()).subscribe(success => {
      this.deleteFavorites();
      this.snackBar.openPanel(`Wish List "${this.saveForm.get('name').value}" created!`, 'SUCCESS');
      this.dialogRef.close();
    }, error => {
      this.snackBar.openPanel('Error while trying to create the wish list', 'ERROR');
      console.log(error);
    });
  }

  private updateList(): void {
    const url = EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-lists/${this.saveForm.get('id').value}`);
    this.http.post(url, this.createWishList()).subscribe(success => {
      console.log(success);
      this.deleteFavorites();
      this.snackBar.openPanel(`Wish List updated correctly!"`, 'SUCCESS');
      this.dialogRef.close();
    }, error => {
      this.snackBar.openPanel('Error while trying to create the wish list', 'ERROR');
      console.log(error);
    });
  }

  createWishList(): WishListView {
    const wishList = new WishListView();
    wishList.createdDate = new Date();
    wishList.userId = AuthUtil.getId();
    wishList.products = [];
    this.products.forEach(product => {
      wishList.products.push(product.productId);
    });
    wishList.name = this.saveForm.get('name').value;
    return wishList;
  }

  deleteFavorites(): void {
    this.products.forEach(product => this.favorites.removeFavorite(product));
  }

}
