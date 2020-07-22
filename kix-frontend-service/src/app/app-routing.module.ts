import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductFormComponent } from './product-form/product-form.component';
import { ViewProductsComponent } from './view-products/view-products.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductView } from './models';
import { MainComponent } from './main/main.component';
import { ProductListComponent } from './product-list/product-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { ErrorNotfoundComponent } from './error-notfound/error-notfound.component';


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
        path: '**',
        component: ErrorNotfoundComponent
      }
    ]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: '',
        component: ProductListComponent
      },
      {
        path: 'products',
        component: ProductListComponent
      },
      {
        path: 'products/create',
        component: ProductFormComponent
      },
      {
        path: 'users',
        component: UserFormComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
