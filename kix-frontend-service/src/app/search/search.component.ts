import { Component, OnInit, AfterViewInit, AfterViewChecked } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Search } from '../models';
import { EdgeURL } from '../utils';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  imageRegion: HTMLElement = document.getElementById('drop_zone');
  imageInput: HTMLInputElement;
  image: File;
  search: Search;

  searching = false;
  searchingError = false;
  errorCode = '';

  constructor(
    private http: HttpClient,
    public dialogRef: MatDialogRef<SearchComponent>
  ) {
    this.imageInput = document.createElement('input');
    this.imageInput.type = 'file';
    this.imageInput.accept = 'image/*';
    this.imageInput.multiple = false;
    this.imageInput.addEventListener('change', () => {
      this.handleImage(this.imageInput.files[0]);
    });
    this.search = new Search();
  }

  ngOnInit(): void {
  }


  removeDragData(ev): void {
    console.log('Removing drag data');

    if (ev.dataTransfer.items) {
      // Use DataTransferItemList interface to remove the drag data
      ev.dataTransfer.items.clear();
    } else {
      // Use DataTransfer interface to remove the drag data
      ev.dataTransfer.clearData();
    }
  }

  openImage(): void {
    this.imageInput.click();
  }

  preventDefault(event: Event): void {
    event.preventDefault();
    event.stopPropagation();
  }
  
  convertImage(image: File) {
    const reader: FileReader = new FileReader();
    reader.readAsDataURL(image);
    reader.onload = (event) => {
       this.searchImage(event.target.result.toString());
    };
  }

  handleImage(image: File): void {
    this.searchingError = !( this.searching = true);
    if (this.validateImage(image)) {
      this.convertImage(image);
    }
  }

  handleDrop(event: DragEvent): void {
    event.preventDefault();
    const data = event.dataTransfer;
    const files = data.files;
    console.log(this.handleImage);
    this.handleImage(files[0]);
    //this.removeDragData(event);
  }

  handleDragOver(event: DragEvent): void {
    event.preventDefault();
  }

  validateImage(image: File): boolean {
    const validTypes: string[] = ['image/jpeg', 'image/png', 'image/gif'];
    if (validTypes.indexOf(image.type) === -1) {
      alert('Invalid image Type!');
      return false;
    }
    return true;
  }

  searchImage(image: string): void{
    const imageBase64: string = image.replace(/data:image\/.*;base64,/, '');
    console.log(imageBase64);
    this.search.imageSearch = true;
    this.search.search = imageBase64;
    console.log(this.search);
    this.http.post(EdgeURL.concat('/products/search'), this.search).subscribe(success => {
      this.dialogRef.close(success);
    }, error => {
      this.searching = !( this.searchingError = true);
      this.errorCode = error.name;
    });
  }

}
