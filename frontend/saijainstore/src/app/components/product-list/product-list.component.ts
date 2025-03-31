import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-product-list',
  imports: [CommonModule, HeaderAdminComponent, RouterModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];


  constructor(private productService: ProductService) { }
  ngOnInit(): void {
    this.getListProducts();
  }

  getListProducts(): void {
    this.productService.getAllProducts()
      .subscribe(products => this.products = products);
  }


  deleteProductById(productId: number): void {
    this.productService.deleteProduct(productId)
      .subscribe(() => this.getListProducts());
  }
}