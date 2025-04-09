import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { CategoryComponent } from './components/category/category.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'users', component: UsersComponent },
    { path: 'admin/products', component: ProductListComponent },
    { path: 'admin/products/addproduct', component: AddProductComponent },
    { path: 'admin/products/updateproduct/:id', component: AddProductComponent },
    { path: 'admin/categories', component: CategoryComponent },
    { path: '**', redirectTo: '' }
];
