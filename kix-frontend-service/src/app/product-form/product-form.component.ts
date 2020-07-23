import { Component, OnInit, Input, Output, EventEmitter, SecurityContext } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Product, ProductView } from '../models';
import { EdgeURL } from '../utils';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  _product: FormGroup;

  @Output() loading = new EventEmitter<boolean>();

  @Output()
  product = new EventEmitter<Product>();

  @Input() updateProduct: Observable<ProductView>;

  categories = [
    {
      category: 'Dresses',
      value: 'dress'
    },
    {
      category: 'Tops & Bodysuits',
      value: 'tops'
    },
    {
      category: 'Shorts',
      value: 'short'
    },
    {
      category: 'Blouses & Shirts',
      value: 'blouses'
    },
    {
      category: 'Skirts',
      value: 'skirt'
    },
    {
      category: 'Jeans',
      value: 'jeans'
    },
    {
      category: 'Swimsuits & Bikinis',
      value: 'swimsuits'
    }
  ];

  colors = [
    {
      color: 'White',
      value: 'white',
      hex: '#fff'
    },
    {
      color: 'Black',
      value: 'black',
      hex: '#000',
    },
    {
      color: 'Ice',
      value: 'ice',
      hex: '#e4e3df'
    },
    {
      color: 'Blue',
      value: 'blue',
      hex: '#4f789d'
    },
    {
      color: 'Anthracite Gray',
      value: 'anthracite-gray',
      hex: '#7d7879'
    },
    {
      color: 'Mustard',
      value: 'mustard',
      hex: '#ffa700'
    },
    {
      color: 'Medium Green',
      value: 'medium-green',
      hex: '#717661'
    },
    {
      color: 'Dark Orange',
      value: 'dark-orange',
      hex: '#ff7000'
    }
  ];

  images: Image[] = [];

  constructor(
    private fb: FormBuilder,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.categories.sort((a, b) => {
      if (a.value > b.value) return 1;
      if (a.value < b.value) return -1;
      return 0;
    });
    this._product = this.fb.group({
      productName: ['', [Validators.required]],
      productDescription: ['', [Validators.required]],
      productPrice: ['', [Validators.required]],
      productCategory: ['', [Validators.required]],
      productColor: ['', [Validators.required]],
    });

    if (this.updateProduct) {
      this.updateProduct.subscribe(product => {
        console.log(product);
        this._product.setValue({
          productName: product.productName,
          productDescription: product.productDescription,
          productPrice: product.productPrice,
          productCategory: '',
          productColor: ''
        });
        product.productImages.images.forEach(element => {
          let image: Image = new Image();
          fetch(element)
            .then(response => response.blob())
            .then(blob => new Promise((resolve, reject) => {
              const reader = new FileReader();
              reader.onloadend = () => {
                image.image = reader.result.toString().replace('data:application/octet-stream;base64,', '');
                this.images.push(image);
                if(this.images.length === product.productImages.images.length){
                  this.loading.emit(false);
                }
              }
              reader.onerror = reject
              reader.readAsDataURL(blob);
            }));
        });
      });
    }
  }

  orderImage(event: CdkDragDrop<string[]>): void {
    moveItemInArray(this.images, event.previousIndex, event.currentIndex);
  }

  sendProduct(): void {
    const images: string[] = [];
    this.images.forEach(image => images.push(image.image));
    const product: Product = new Product(this._product.value, images);
    this.product.emit(product);
  }

  deleteImage(index: number): void {
    this.images.splice(index, 1);
  }

  addImage(image: Image): void {
    this.images.push(image);
  }

  insertImage(): void {
    document.getElementById('insert-image').click();
    document.getElementById('insert-image').onchange = (e) => this.readFile();
  }

  readImage(file: File): void {
    const reader = new FileReader();
    const image: Image = new Image();
    reader.addEventListener('load', () => {
      image.image = Image.getImageData(reader.result.toString());
      image.name = file.name;
      image.size = file.size;
      this.addImage(image);
    }
    );
    reader.readAsDataURL(file);
  }

  readFile(): void {
    const files = (document.getElementById('insert-image') as HTMLInputElement).files;
    if (files) {
      [].forEach.call(files, this.readImage.bind(this));
    }
  }

  getBase64FromImageUrl(img: HTMLImageElement): string {
    const canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;
    const ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);
    const dataURL = canvas.toDataURL('image/jpg');
    return dataURL.replace(/^data:image\/(png|jpg);base64,/, '');
  }

}


class Image {
  name: string;
  image: string;
  size: number;

  constructor(image?: string) {
    this.image = image;
  }

  static getImageData(base64: string): string {
    return base64.replace('data:image/jpeg;base64,', '');
  }
}
