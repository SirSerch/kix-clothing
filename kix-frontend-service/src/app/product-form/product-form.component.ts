import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models';
import { EdgeURL } from '../utils';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  product: FormGroup;

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
      value: 'bikinis'
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
    private http: HttpClient,
  ) { }

  ngOnInit(): void {
    this.product = this.fb.group({
      productName: ['', [Validators.required]],
      productDescription: ['', [Validators.required]],
      productPrice: ['', [Validators.required]],
      productCategory: ['', [Validators.required]],
      productColor: ['', [Validators.required]],
    });
  }

  createProduct(): void {
    const images: string[] = [];
    this.images.forEach(image => images.push(image.image));
    const product: Product = new Product(this.product.value, images);
    this.http.post(EdgeURL.concat('/product'), product).subscribe( result => {
      console.log(result);
    },
    error => {
      console.log(error);
    });
  }

  deleteImage(index: number): void{
    this.images.splice(index, 1);
  }

  addImage(image: Image): void{
    this.images.push(image);
  }

  insertImage(): void {
    document.getElementById('insert-image').click();
    document.getElementById('insert-image').onchange = (e) => this.readFile();
  }

  readImage(file: File): void{
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
}

class Image{
  name: string;
  image: string;
  size: number;

  static getImageData(base64: string): string{
      return base64.replace('data:image/jpeg;base64,', '');
  }
}
