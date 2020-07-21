import { Component, OnInit, ViewChild, Inject, ChangeDetectorRef } from '@angular/core';
import { ProductView } from '../models';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EdgeURL } from '../utils';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { IndexProductDialogComponent } from '../index-product-dialog/index-product-dialog.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'price', 'image', 'indexed', 'view'];

  productList: ProductView[] = [];

  dataSource = new MatTableDataSource();

  isLoading = true;
  isErrorLoading = false;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.isErrorLoading = !(this.isLoading = true);
    this.http.get<ProductView[]>(EdgeURL.concat('/products')).subscribe(
      products => {
        console.log(products);
        this.isLoading = false;
        this.productList = products;
        this.dataSource = new MatTableDataSource(products);
      },
      error => {
        this.isLoading = !(this.isErrorLoading = true);
        console.log(error);
      }
    );
    this.dataSource.sort = this.sort;
  }

  indexProduct(product: ProductView, tableIndex: number): void {
    const dialogRef = this.dialog.open(IndexProductDialogComponent, {
      width: '400px',
      data: product
    });
    dialogRef.afterClosed().subscribe( productResult => {
      if (productResult !== undefined) {
        console.log(productResult);
        this.productList.splice(tableIndex, 1, productResult);
        this.dataSource.data = this.productList;
      }
    });
  }

}



