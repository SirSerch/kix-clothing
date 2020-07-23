import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ProductView } from '../models';
import { EdgeURL, SnackBar } from '../utils';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-showcase',
  templateUrl: './product-showcase.component.html',
  styleUrls: ['./product-showcase.component.css']
})
export class ProductShowcaseComponent implements OnInit {

  product: ProductView;
  snackBar: SnackBar;

  constructor(
    private routerParam: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    snackBar: MatSnackBar
    ) {
      this.snackBar = new SnackBar(snackBar);
    }

  ngOnInit(): void { 
    this.routerParam.paramMap.subscribe(params => {
      this.http.get<ProductView>(EdgeURL.concat(`/products/${params.get('id')}`)).subscribe(success => {
        console.log(success);
        this.product = success;
      }, error => {
        this.snackBar.openPanel(`Error while tying to get product: ${params.get('id')}`, 'ERROR');
      });
    });
   }

}
