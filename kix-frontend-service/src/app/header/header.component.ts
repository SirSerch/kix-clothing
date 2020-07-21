import { Component, OnInit, Input } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { renderFlagCheckIfStmt } from '@angular/compiler/src/render3/view/template';
import { StorageService } from '../storage.service';

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
  favorites = 0;


  constructor(
      router: Router,
      private storageService: StorageService
  ) { 
    this.router = router;
    this.favorites = this.storageService.obtainFavorites().length;
    this.favorites > 0 ? this.hasFavorite = true : this.hasFavorite = false;
  }

  ngOnInit(): void {
    this.storageService.watchStorage().subscribe((data: string) => {
        parseInt(data) > 0 ? this.hasFavorite = true : this.hasFavorite = false;
        this.favorites = parseInt(data);
    });
  }

  logOut(): void {
    this.router.navigate[''];
  }

  insertAlgo(data: string):void {
    this.storageService.setItem('products', data);
  }

}
