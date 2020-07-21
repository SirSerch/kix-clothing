import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexProductDialogComponent } from './index-product-dialog.component';

describe('IndexProductDialogComponent', () => {
  let component: IndexProductDialogComponent;
  let fixture: ComponentFixture<IndexProductDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndexProductDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexProductDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
