import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ProductView } from '../models';
import { StorageService } from '../storage.service';
import { MatDialog } from '@angular/material/dialog';
import { WishlistSaveComponent } from '../wishlist-save/wishlist-save.component';
import { AuthUtil } from '../utils';

@Component({
  selector: 'app-view-wish-list',
  templateUrl: './view-wish-list.component.html',
  styleUrls: ['./view-wish-list.component.css']
})
export class ViewWishListComponent implements OnInit {

  @Output()
  propagar = new EventEmitter<ProductView>();

  wishList: ProductView[];

  auth = AuthUtil.isAuth();

  constructor(
    private storage: StorageService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.obtainWishList();
    this.storage.watchStorage().subscribe(updated => {
      this.obtainWishList();
    })
  }

  obtainWishList(): void{
    this.wishList = this.storage.obtainFavorites();
  }

  deleteProduct(product: ProductView){
    this.storage.removeFavorite(product);
  }

  deleteWishList(){
    this.storage.obtainFavorites().forEach(favorite => this.deleteProduct(favorite));
  }

  saveWishList(){
    const dialogRef = this.dialog.open(WishlistSaveComponent, {
      width: '400px',
      data: this.wishList
    });
  }

}
