import { Component, OnInit } from '@angular/core';
import { ProductView } from '../models';
import { HttpClient } from '@angular/common/http';
import { EdgeURL } from '../utils';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {

  products: ProductView[] = [];

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts(){
    this.http.get<ProductView[]>(EdgeURL.concat('/products')).subscribe(products => {
      this.products = products;
      console.log(this.products);
    },
    error => console.log(error));
  }

}
