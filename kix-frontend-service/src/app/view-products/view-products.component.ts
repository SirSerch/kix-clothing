import { Component, OnInit, Output } from '@angular/core';
import { ProductView, Favorite, FavoriteAction, Product } from '../models';
import { HttpClient } from '@angular/common/http';
import { EdgeURL } from '../utils';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {

  noProducts: boolean
  loading: boolean
  error: boolean

  products: ProductView[] = [];

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  ngOnInit(): void {
    this.getAllProducts();
    this.storage.watchStorage().subscribe(update => {
      this.products.find(product => product.productId === update.productId).isFavorite = update.isFavorite;
    });
  }

  getAllProducts(): void {
    this.error = !(this.loading = true);
    this.http.get<ProductView[]>(EdgeURL.concat('/products')).subscribe(products => {
      if (products.length === 0) this.noProducts = true;
      this.products = this.storage.setProducts(products);
      this.loading = false;
    },
      error => {
        this.error = true;
        console.log(error);
      });
  }

  favorite(favorite: Favorite): void {
    if (favorite.action === FavoriteAction.ADD) {
      this.storage.setFavorite(favorite.product);
    }
    if (favorite.action === FavoriteAction.REMOVE) {
      this.storage.removeFavorite(favorite.product);
    }
  }

}
