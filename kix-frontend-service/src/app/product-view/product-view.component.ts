import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Product, ProductView } from '../models';
import { FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { EdgeURL } from '../utils';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {

  _product: ProductView;
  productId: string;

  product?: Subject<ProductView> = new Subject<ProductView>();



  constructor(
    private http: HttpClient,
    private routerParam: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.routerParam.paramMap.subscribe(params => {
      this.http.get<ProductView>(EdgeURL.concat(`/products/${params.get('product')}`)).subscribe(product => {
        this._product = product;
        this.product.next(product);
      },
      error => {
        this.openPanel(`Can't obtain the product whith id: ${params.get('product')}`, 'ERROR');
        this.router.navigate(['products']);
      });
    });
  }

  updateProduct(product: Product){
    
  }

  openPanel(message: string, action: string): void{
    this.snackBar.open(message, action, {
      duration: 10000,
      verticalPosition: 'top',
      horizontalPosition: 'start',
      panelClass: action !== 'ERROR' ? 'panel-success' : 'panel-error'
    });
  }

}
