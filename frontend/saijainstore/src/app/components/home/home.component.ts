import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { RouterLink } from '@angular/router';
import { HeaderUserComponent } from "../header-user/header-user.component";
import { HomeService } from '../../services/home.service';
import { SessionStorageService } from '../../services/session-storage.service';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { ProductFiltersComponent } from "../product-filters/product-filters.component";

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, HeaderUserComponent, HeaderAdminComponent, ProductFiltersComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  //products: Product[] = [];
  urlDetailProduct: string = '/cart/detailproduct';
  userType: string | null = null;
  products$: Observable<Product[]>;


  constructor(private homeService: HomeService, private sessionStorage: SessionStorageService) {
    this.products$ = this.homeService.getAllProducts();
  }

  ngOnInit(): void {
    //this.productService.getAllProducts().subscribe(products => this.products = products);
    this.userType = this.sessionStorage.getItem('userType');
    //console.log(this.userType, this.sessionStorage.getItem('userId'));
  }

  onFiltersChanged(filters: any) {
    const { name, price, categoryId } = filters;
    const parsedCategoryId = categoryId ? Number(categoryId) : undefined;
    const parsedPrice = price !== null && price !== '' ? Number(price) : undefined;

    this.products$ = this.homeService.searchProducts(name, parsedPrice, parsedCategoryId);
  }
}
