import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductView } from '../models';
import { HttpClient } from '@angular/common/http';
import { EdgeURL, SnackBar } from '../utils';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-delete',
  templateUrl: './product-delete.component.html',
  styleUrls: ['./product-delete.component.css']
})
export class ProductDeleteComponent implements OnInit {

  product: ProductView;
  snackBar: SnackBar;
  deleting: boolean = false;
  errorDeleting: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<ProductDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) product: ProductView,
    private http: HttpClient,
    snackBar: MatSnackBar
  ) { 
    this.product = product;
    this.snackBar = new SnackBar(snackBar);
  }

  ngOnInit(): void {
  }

  deleteProduct(){
    this.errorDeleting = !(this.deleting = true);
    this.http.delete(EdgeURL.concat('/products/'+ this.product.productId)).subscribe(success => {
      this.snackBar.openPanel('Product ' + this.product.productId.substring(0,7) + ' Correctly!', 'SUCCESS');
      this.dialogRef.close(true);
    },
    error => {
      this.deleting = !(this.errorDeleting = true);
      this.snackBar.openPanel('An error occured while trying to delete product: ' + this.product.productId.substring(0, 7), 'ERROR');
      this.dialogRef.close(false);
    });
  }

}
