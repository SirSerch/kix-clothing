import { Component, OnInit, Input } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { renderFlagCheckIfStmt } from '@angular/compiler/src/render3/view/template';
import { StorageService } from '../storage.service';
import { verifyHostBindings } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
import { SearchComponent } from '../search/search.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input()
  main: string;

  @Input()
  navbar: MatSidenav;
  router: Router;

  hasFavorite = false;

  searchData: Search;


  constructor(
      router: Router,
      public favoriteService: StorageService,
      public dialog: MatDialog
  ) { 
    this.router = router;
    favoriteService.favorites > 0 ? this.hasFavorite = true : this.hasFavorite = false;
  }

  ngOnInit(): void {
    this.favoriteService.watchStorage().subscribe((data: string) => {
      this.favoriteService.favorites > 0 ? this.hasFavorite = true : this.hasFavorite = false;
    });
  }

  logOut(): void {
    this.router.navigate[''];
  }

  insertAlgo(data: string):void {
    this.favoriteService.setItem('products', data);
  }

  search(){
    const dialogRef = this.dialog.open(SearchComponent, {
      data: this.searchData,
      width: '90%',
      height: '400px',
      minWidth: '250px',
      maxWidth: '400px'
    });
    dialogRef.afterClosed().subscribe( searchResult => {
      if (searchResult !== undefined) {
        console.log(searchResult);
        this.router.navigateByUrl('/products/search', {state: searchResult});
      }
    });
  }

}

export interface Search{
  isImageSearch: boolean;
  filter: {
    category: string,
    color: string;
  };
  searchBody: string;
}
