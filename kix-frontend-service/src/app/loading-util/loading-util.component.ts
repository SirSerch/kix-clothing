import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-loading-util',
  templateUrl: './loading-util.component.html',
  styleUrls: ['./loading-util.component.css']
})
export class LoadingUtilComponent implements OnInit {

  @Input()
  loading?: boolean = true;

  @Input()
  error?: boolean = false;

  @Input()
  goBack: string = '';

  @Input()
  title?: string = 'LOADING';

  @Input()
  errorMessage?: string = 'An error has occurred while trying to do the action';

  constructor() { }

  ngOnInit(): void {
  }

}
