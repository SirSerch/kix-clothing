import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { AuthUtil } from '../utils';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  mobileQuery: MediaQueryList;
  shouldRun = true;
  private _mobileQueryListener: () => void;

  fillerNav: Nav[];

  adminNav = [
    {
      name: 'Products',
      route: 'products',
      icon: 'store',
      enable: true
    },
    {
      name: 'Users',
      route: 'users',
      icon: 'supervisor_account',
      enable: true
    }
  ];

  clientNav = [
    {
      name: 'Wish lists',
      route: 'wishlists',
      icon: 'favorite',
      enable: true
    },
    {
      name: 'Orders',
      route: 'orders',
      icon: 'shopping_cart',
      enable: false
    },
    {
      name: 'Payments',
      route: 'payments',
      icon: 'payment',
      enable: false
    },
  ]


  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private router: Router
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this._mobileQueryListener);
    this.checkAuth();
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    // tslint:disable-next-line: deprecation
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  checkAuth(): void{
    if (AuthUtil.isAuth()) {
      console.log(AuthUtil.isAdmin());
      if (AuthUtil.isAdmin()) {
        this.router.navigate(['dashboard/products']);
        this.fillerNav = this.adminNav;
      } else {
        this.router.navigate(['dashboard/wishlists']);
        this.fillerNav = this.clientNav;
      }
    } else {
      this.router.navigate(['']);
    }
  }

}

export class Nav {
  name: string;
  route: string;
  icon: string;
  enable: boolean;
}
