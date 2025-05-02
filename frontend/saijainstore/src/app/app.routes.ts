import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { CategoryComponent } from './components/category/category.component';
import { DetailProdcutComponent } from './components/cart/detail-prodcut/detail-prodcut.component';
import { SumaryOrderComponent } from './components/orders/sumary-order/sumary-order.component';
import { PaymentSuccesComponent } from './components/payment-succes/payment-succes.component';
import { RegistrationComponent } from './components/authentication/registration/registration.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { authGuard } from './guards/auth.guard';
import { OrdersComponent } from './components/orders/orders.component';
import { authAdminGuard } from './guards/auth-admin.guard';

export const routes: Routes = [

    { path: '', component: HomeComponent },
    // ADMIN routes
    {
        path: 'admin',
        canActivate: [authAdminGuard],
        children: [
            { path: 'users', component: UsersComponent },
            { path: 'orders', component: OrdersComponent },
            { path: 'products', component: ProductListComponent },
            { path: 'products/addproduct', component: AddProductComponent },
            { path: 'products/updateproduct/:id', component: AddProductComponent },
            { path: 'categories', component: CategoryComponent }
        ]
    },

    // USER routes
    {
        path: 'cart',
        canActivate: [authGuard],
        children: [
            { path: 'detailproduct/:id', component: DetailProdcutComponent },
            { path: 'sumary', component: SumaryOrderComponent }
        ]
    },

    // Public routes
    { path: 'payment/success', component: PaymentSuccesComponent },
    { path: 'register', component: RegistrationComponent },
    { path: 'login', component: LoginComponent },
    { path: 'logout', component: LogoutComponent },

    // Fallback
    { path: '**', redirectTo: '' }
];
