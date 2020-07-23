import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductFormComponent } from './product-form/product-form.component';
import { ViewProductsComponent } from './view-products/view-products.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserViewComponent } from './user-view/user-view.component';
import { MainComponent } from './main/main.component';
import { ProductListComponent } from './product-list/product-list.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { ErrorNotfoundComponent } from './error-notfound/error-notfound.component';
import { SearchViewComponent } from './search-view/search-view.component';
import { ProductCreateComponent } from './product-create/product-create.component';
import { ProductViewComponent } from './product-view/product-view.component';
import { ProductShowcaseComponent } from './product-showcase/product-showcase.component';
import { UserListComponent } from './user-list/user-list.component';


const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: ViewProductsComponent
      },
      {
        path: 'products/search',
        component: SearchViewComponent
      },
      {
        path: 'products/:id',
        component: ProductShowcaseComponent
      }
    ]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: '',
        redirectTo: 'products',
        pathMatch: 'full'
      },
      {
        path: 'products',
        component: ProductListComponent,
      },
      {
        path: 'products/new',
        component: ProductCreateComponent
      },
      {
        path: 'products/:product',
        component: ProductViewComponent
      },
      {
        path: 'users',
        component: UserListComponent
      },
      {
        path: 'users/new',
        component: UserCreateComponent
      },
      {
        path: 'users/:id',
        component: UserViewComponent
      },
    ]
  },
  {
    path: '**',
    component: ErrorNotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
