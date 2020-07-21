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

  constructor() { }

  ngOnInit(): void {
  }

}
