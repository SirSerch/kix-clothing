import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProductView, Product } from '../models';
import { SimpleSnackBar, MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { EdgeURL } from '../utils';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  product: FormGroup;

  constructor(
    private snackBar: MatSnackBar,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  createProduct(product: Product): void {
    this.http.post<ProductView>(EdgeURL.concat('/product'), product).subscribe( result => {
      this.openPanel(`Product ${result.productId.substring(0,8)} created correctly!`, 'SUCCESS')
      this.router.navigate(['products']);
    },
    error => {
      this.openPanel('An error occurred while creating the product, please try again later', 'ERROR')
      this.router.navigate(['dashboard/products']);
      console.log(error);
    });
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
