import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { ProductView } from './models';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  favorites = 0;

  private storageSub = new Subject<String>();


  constructor() { 
    this.favorites = this.obtainFavorites().length;
  }

  watchStorage(): Observable<any> {
    return this.storageSub.asObservable();
  }

  setItem(key: string, data: any): void {
    localStorage.setItem(key, data);
    this.storageSub.next('changed');
  }

  removeItem(key): void {
    localStorage.removeItem(key);
    this.storageSub.next('changed');
  }

  setFavorite(product: ProductView): void {
    const favorite: ProductView[] = this.obtainFavorites();
    favorite.push(product);
    localStorage.setItem('favorites', JSON.stringify(favorite));
    this.favorites++;
    product.isFavorite = true;
    this.storageSub.next(this.favorites.toString());
  }

  removeFavorite(product: ProductView): void {
    const favorite: ProductView[] = this.obtainFavorites();
    localStorage.setItem('favorites', JSON.stringify(this.deleteFromArray(product.productId, favorite)));
    this.favorites--;
    product.isFavorite = false;
    this.storageSub.next(this.favorites.toString());
  }

  obtainFavorites(): ProductView[] {
    let favorite: ProductView[] = JSON.parse(localStorage.getItem('favorites'));
    if (favorite === null) {
      return [];
    } else {
      return favorite;
    }
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
