import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { RouterLink } from '@angular/router';
import { HeaderUserComponent } from "../header-user/header-user.component";
import { HomeService } from '../../services/home.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, HeaderUserComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  //products: Product[] = [];
  urlDetailProduct: string = '/cart/detailproduct';

  products$: Observable<Product[]>;


  constructor(private homeService: HomeService) {
    this.products$ = this.homeService.getAllProducts();
  }

  ngOnInit(): void {
    //this.productService.getAllProducts().subscribe(products => this.products = products);

  }
}
