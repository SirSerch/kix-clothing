import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ProductView } from '../models';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-view-wish-list',
  templateUrl: './view-wish-list.component.html',
  styleUrls: ['./view-wish-list.component.css']
})
export class ViewWishListComponent implements OnInit {

  @Output()
  propagar = new EventEmitter<ProductView>();
  
  wishList: ProductView[];

  constructor(
    private storage: StorageService
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

}
