import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  fillerNav = [
    {
      name: 'Blouses & Shirts',
      route: '',
    },
    {
      name: 'Dresses',
      route: '',
    },
    {
      name: 'Jeans',
      route: '',
    },
    {
      name: 'Shorts',
      route: '',
    },
    {
      name: 'Skirts',
      route: '',
    },
    {
      name: 'Swimsuits & Bikinis',
      route: '',
    },
    {
      name: 'Tops & Bodysuits',
      route: '',
    },
  ];

  mobileQuery: MediaQueryList;
  shouldRun = true;
  private _mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
  ) { 
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    // tslint:disable-next-line: deprecation
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

}
