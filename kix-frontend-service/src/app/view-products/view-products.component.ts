import { Component, OnInit } from '@angular/core';
import { ProductView } from '../models';
import { HttpClient } from '@angular/common/http';
import { EdgeURL } from '../utils';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {

  products: ProductView[] = [];

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    const favorites: ProductView[] = this.storage.obtainFavorites();
    this.http.get<ProductView[]>(EdgeURL.concat('/products')).subscribe(products => {
      favorites.forEach(favorite => {
        products.find(product => product.productId === favorite.productId).isFavorite = true;
      });
      this.products = products;
    },
      error => console.log(error));
  }

  setFavorite(product: ProductView) {
    this.storage.setFavorite(product);
  }

  removeFavorite(product: ProductView) {
    this.storage.removeFavorite(product);
  }

}
