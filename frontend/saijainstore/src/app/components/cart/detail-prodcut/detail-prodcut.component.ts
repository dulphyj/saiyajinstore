import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Product } from '../../../common/product';
import { ProductService } from '../../../services/product.service';
import { FormsModule } from '@angular/forms';
import { CartService } from '../../../services/cart.service';
import { ItemCart } from '../../../common/item-cart';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-detail-prodcut',
  imports: [CommonModule, RouterLink, FormsModule, HeaderUserComponent],
  templateUrl: './detail-prodcut.component.html',
  styleUrl: './detail-prodcut.component.css'
})
export class DetailProdcutComponent implements OnInit {

  product: Product | null = null;
  quantity: number = 1;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private toastr: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getProductById(id).subscribe(prod => this.product = prod);
  }

  addCart() {
    if (this.product) {
      let item = new ItemCart(
        this.product.id!,
        this.product.name,
        this.product.price,
        this.quantity
      );
      console.log('before ', item, 'total ', this.cartService.totalCart());
      this.cartService.addItemCart(item);
      this.cartService.totalCart();
      console.log('after ', item, 'total ', this.cartService.totalCart());
      this.toastr.success('Product added to cart!', 'Success');
    }
  }
}
