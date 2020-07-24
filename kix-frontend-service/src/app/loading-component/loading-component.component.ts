import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-loading-component',
  templateUrl: './loading-component.component.html',
  styleUrls: ['./loading-component.component.css']
})
export class LoadingComponentComponent implements OnInit {

  @Input()
  isErrorLoading: boolean;

  @Input()
  isLoading: boolean;

  @Input()
  isEmpty?: boolean = false;

  @Input()
  title?: string = `Wooow, there's too much wasted space here...`;

  @Input()
  subtitle?: string = `Why don't you create something new?`;

  constructor() { }

  ngOnInit(): void {
  }

}
