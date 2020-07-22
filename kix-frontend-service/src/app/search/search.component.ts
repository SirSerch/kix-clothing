import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  dropHandler(event) {
    console.log('File Dropped!');
    event.preventDefault();
    if (event.dataTransfer.items) {
      if (event.dataTransfer.items.length === 1) {
        if (event.dataTransfer.items[0].kind === 'file') {
          var file = event.dataTransfer.items[0].getAsFile();
          console.log('... file[' + 0 + '].name = ' + file.name);
        } else {
          console.log('Please use only images');
        }
      } else {
        console.log('Please insert only one image');
      }
    } else {
      if (event.dataTransfer.files.length === 1) {
        console.log('... file[' + 0 + '].name = ' + event.dataTransfer.files[0].name);
      } else {
        console.log('Error, only accept one image');
      }

    }
        // Pass event to removeDragData for cleanup
        this.removeDragData(event);
  }

dragOverHandler(event): void {
  console.log('File(s) in drop zone');
  event.preventDefault();

}

removeDragData(ev): void {
  console.log('Removing drag data')

  if (ev.dataTransfer.items) {
    // Use DataTransferItemList interface to remove the drag data
    ev.dataTransfer.items.clear();
  } else {
    // Use DataTransfer interface to remove the drag data
    ev.dataTransfer.clearData();
  }
}

}
