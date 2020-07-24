import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { ProductView } from './models';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  public favorites = 0;

  private storageSub = new Subject<ProductView>();


  constructor() {
    this.favorites = this.obtainFavorites().length;
  }

  watchStorage(): Observable<any> {
    return this.storageSub.asObservable();
  }

  setItem(key: string, data: any): void {
    localStorage.setItem(key, data);
    this.storageSub.next();
  }

  removeItem(key): void {
    localStorage.removeItem(key);
    this.storageSub.next();
  }

  setFavorite(product: ProductView): void {
    const favorite: ProductView[] = this.obtainFavorites();
    favorite.push(product);
    localStorage.setItem('favorites', JSON.stringify(favorite));
    this.favorites++;
    product.isFavorite = true;
    this.storageSub.next(product);
  }

  removeFavorite(product: ProductView): void {
    const favorite: ProductView[] = this.obtainFavorites();
    localStorage.setItem('favorites', JSON.stringify(this.deleteFromArray(product.productId, favorite)));
    this.favorites--;
    product.isFavorite = false;
    this.storageSub.next(product);
  }

  obtainFavorites(): ProductView[] {
    if (localStorage.getItem('favorites') !== 'undefined') {
      const favorite: ProductView[] = JSON.parse(localStorage.getItem('favorites'));
      if (favorite === null) {
        return [];
      } else {
        return favorite;
      }
    }
    return [];
  }

  deleteAllFavorites(): void {
    this.removeItem('favorites');
    this.storageSub.next();
  }

  setProducts(products: ProductView[]): ProductView[]{
    const favorites: ProductView[] = this.obtainFavorites();
    favorites.forEach(favorite => {
      products.find(product => product.productId === favorite.productId).isFavorite = true;
    });
    return products;
  }

  private deleteFromArray(key: string, array: ProductView[]): ProductView[] {
    for (let i = 0; i < array.length; i++) {
      if (array[i].productId === key) {
        array.splice(i, 1);
        return array;
      }
    }
  }

}
