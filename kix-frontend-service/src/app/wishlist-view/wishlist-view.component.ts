import { Component, OnInit } from '@angular/core';
import { WishListView, Product, ProductView, Favorite, FavoriteAction } from '../models';
import { AuthUtil, EdgeURL, SnackBar } from '../utils';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-wishlist-view',
  templateUrl: './wishlist-view.component.html',
  styleUrls: ['./wishlist-view.component.css']
})
export class WishlistViewComponent implements OnInit {

  wish: WishListView;
  wishListId: string;

  products: ProductView[] = [];

  isLoading = false;
  isErrorLoading = false;

  snackBar: SnackBar;

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
      this.isErrorLoading = !(this.isLoading = true);
      this.http.get<WishListView>(EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-lists/${params.get('id')}`)).subscribe(wish => {
        this.wishListId = params.get('id');
        this.wish = wish;
        this.getWishProducts(wish.products);
      },
        error => {
          console.log(error);
          this.snackBar.openPanel(`Can't obtain the wishList whith id: ${params.get('id')}`, 'ERROR');
          this.router.navigate(['wishlists']);
        });
    });
  }

  getWishProducts(products: string[]) {
    products.forEach(productId => {
      if (this.isErrorLoading) { return this.isErrorLoading; }
      this.http.get<ProductView>(EdgeURL.concat(`/products/${productId}`)).subscribe(success => {
        success.isFavorite = true;
        this.products.push(success);
      }, error => {
        console.log(error);
        this.isErrorLoading = true;
      });
    });
    console.log(this.products);
    this.isLoading = false;
  }

  updateWishList() {
    let newProducts: string[] = this.products.filter(product => product.isFavorite === true).map(product => product.productId);
    let wish = this.wish;
    wish.products = newProducts;
    this.http.put(EdgeURL.concat(`/users/${AuthUtil.getId()}/wish-lists/${wish.id}`), wish).subscribe(success => {
      this.snackBar.openPanel(`Wish list ${wish.name} updated correctly!`, 'SUCCESS');
      this.router.navigate(['dashboard/wishlists']);
    },
    error => {
      this.snackBar.openPanel(`Error while trying to update the wishList, please try again later`, 'ERROR');
      console.log(error);
    });
  }

  favorite(event: Favorite, index) {
    this.products[index].isFavorite = (event.action === FavoriteAction.ADD ? true : false);
  }
}
