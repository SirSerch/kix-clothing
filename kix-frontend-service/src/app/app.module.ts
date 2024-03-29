// Angular Material
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTableModule} from '@angular/material/table';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTreeModule} from '@angular/material/tree';
import {MatBadgeModule} from '@angular/material/badge';
import {MatMenuModule} from '@angular/material/menu';
import {MatTabsModule} from '@angular/material/tabs';
import {MatTooltipModule} from '@angular/material/tooltip';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {MatSnackBarModule} from '@angular/material/snack-bar';
//////////////////

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserFormComponent } from './user-form/user-form.component';
import { MainComponent } from './main/main.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { SearchComponent } from './search/search.component';
import { ViewProductsComponent } from './view-products/view-products.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { ProductListComponent} from './product-list/product-list.component';
import { IndexProductDialogComponent } from './index-product-dialog/index-product-dialog.component';
import { LoadingComponentComponent } from './loading-component/loading-component.component';
import { ViewWishListComponent } from './view-wish-list/view-wish-list.component';
import { ErrorNotfoundComponent } from './error-notfound/error-notfound.component';
import { SearchViewComponent } from './search-view/search-view.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProductViewComponent } from './product-view/product-view.component';
import { ProductCreateComponent } from './product-create/product-create.component';
import { LoadingUtilComponent } from './loading-util/loading-util.component';
import { ProductDeleteComponent } from './product-delete/product-delete.component';
import { ProductShowcaseComponent } from './product-showcase/product-showcase.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { UserViewComponent } from './user-view/user-view.component';
import { UserDeleteComponent } from './user-delete/user-delete.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginSigninComponent } from './login-signin/login-signin.component';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { WishlistListComponent } from './wishlist-list/wishlist-list.component';
import { WishlistSaveComponent } from './wishlist-save/wishlist-save.component';
import { WishlistViewComponent } from './wishlist-view/wishlist-view.component';

@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    MainComponent,
    ProductFormComponent,
    SearchComponent,
    ViewProductsComponent,
    DashboardComponent,
    HeaderComponent,
    ProductListComponent,
    IndexProductDialogComponent,
    LoadingComponentComponent,
    ViewWishListComponent,
    ErrorNotfoundComponent,
    SearchViewComponent,
    ProductCardComponent,
    ProductViewComponent,
    ProductCreateComponent,
    LoadingUtilComponent,
    ProductDeleteComponent,
    ProductShowcaseComponent,
    UserListComponent,
    UserCreateComponent,
    UserViewComponent,
    UserDeleteComponent,
    LoginFormComponent,
    LoginSigninComponent,
    LoginSignupComponent,
    WishlistListComponent,
    WishlistSaveComponent,
    WishlistViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ////// MATERIAL ////////
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    MatGridListModule,
    MatSidenavModule,
    MatToolbarModule,
    MatTableModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatTreeModule,
    MatBadgeModule,
    MatMenuModule,
    MatTabsModule,
    MatTooltipModule,
    DragDropModule,
    MatSnackBarModule
    ///////////////////////
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
