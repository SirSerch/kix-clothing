import { Component, OnInit, Inject } from '@angular/core';
import { EdgeURL } from '../utils';
import { ProductView } from '../models';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductListComponent } from '../product-list/product-list.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-index-product-dialog',
  templateUrl: './index-product-dialog.component.html',
  styleUrls: ['./index-product-dialog.component.css']
})
export class IndexProductDialogComponent implements OnInit {

  deleting = false;
  indexing = false;
  indexingError = false;
  product: ProductView;

  constructor(
    public dialogRef: MatDialogRef<IndexProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) product: ProductView,
    private http: HttpClient
  ) {
    this.product = product;
  }

  ngOnInit(): void {
    console.log(this.product);
  }

  indexProduct(product: ProductView): void {
    this.indexingError = !(this.indexing = true);
    this.http.post(EdgeURL.concat(`/products/${product.productId}/indexing`), null)
      .subscribe(success => {
        this.dialogRef.close(success as ProductView);
      }, error => {
        this.indexingError = !(this.indexing = false);
        console.log(error);
      }
      );
  }

  deleteIndexProduct(product: ProductView): void {
    this.indexingError = !(this.indexing = this.deleting = true);
    this.http.delete(EdgeURL.concat(`/products/${product.productId}/indexing`), {responseType: 'json'})
      .subscribe(success => {
        console.log('success');
        console.log(success);
        this.dialogRef.close(success as ProductView);
      }, error => {
        this.indexingError = !(this.indexing = this.deleting = false);
        console.log(error);
      }
      );
  }

  close(): void{
    this.dialogRef.close();
  }
}
