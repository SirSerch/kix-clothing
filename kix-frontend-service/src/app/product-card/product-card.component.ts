import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ProductView, Favorite, FavoriteAction } from '../models';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Output()
  favorite = new EventEmitter<Favorite>();

  @Input()
  product: ProductView;

  constructor(
    ) {}

  ngOnInit(): void {
  }

  setFavorite(action: FavoriteAction): void{
      this.favorite.emit(new Favorite(action, this.product));
  }

}
