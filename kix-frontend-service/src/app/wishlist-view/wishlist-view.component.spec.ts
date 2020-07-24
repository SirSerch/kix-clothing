import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistViewComponent } from './wishlist-view.component';

describe('WishlistViewComponent', () => {
  let component: WishlistViewComponent;
  let fixture: ComponentFixture<WishlistViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WishlistViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
