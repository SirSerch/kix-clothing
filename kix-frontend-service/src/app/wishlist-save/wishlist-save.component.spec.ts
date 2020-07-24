import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistSaveComponent } from './wishlist-save.component';

describe('WishlistSaveComponent', () => {
  let component: WishlistSaveComponent;
  let fixture: ComponentFixture<WishlistSaveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WishlistSaveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
