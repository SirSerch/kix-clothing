import { Component, OnInit } from '@angular/core';
import { ProductView, Favorite, FavoriteAction } from '../models';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-search-view',
  templateUrl: './search-view.component.html',
  styleUrls: ['./search-view.component.css']
})
export class SearchViewComponent implements OnInit {

  searchResult: ProductView[] = [];

  releatedResult: ProductView[] = [];
  bestResults: ProductView[] = [];

  constructor(
    private storage: StorageService,
    private router: Router,
    private location: Location
  ) {
    if (router.getCurrentNavigation() !== null) {
      this.searchResult = (router.getCurrentNavigation().extras.state as ProductView[]);
    }
  }

  ngOnInit(): void {
    if (this.searchResult.length === 0) {
      this.location.back();
    }
    this.searchResult = this.storage.setProducts(this.searchResult);
    this.searchResult.sort((a, b) => b.score - a.score);
    this.bestResults = this.searchResult.filter(result => result.score > 0.5);
    this.releatedResult = this.searchResult.filter(result => result.score >= 0.27 && result.score < 0.5);
    this.storage.watchStorage().subscribe(update => {
      this.releatedResult.find(product => product.productId === update.productId).isFavorite = update.isFavorite;
      this.bestResults.find(product => product.productId === update.productId).isFavorite = update.isFavorite;
    });
  }

  favorite(favorite: Favorite): void{
    if (favorite.action === FavoriteAction.ADD){
      this.storage.setFavorite(favorite.product);
    }
    if (favorite.action === FavoriteAction.REMOVE){
      this.storage.removeFavorite(favorite.product);
    }
  }

}
