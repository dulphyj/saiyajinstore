import { Component, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { ItemCart } from '../../../common/item-cart';
import { User } from '../../../common/user';
import { CartService } from '../../../services/cart.service';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-sumary-order',
  imports: [HeaderUserComponent, CommonModule],
  templateUrl: './sumary-order.component.html',
  styleUrl: './sumary-order.component.css'
})
export class SumaryOrderComponent implements OnInit {

  items: ItemCart[] = [];
  totalCart: number = 0;
  user: User = new User(null, '', '', '', '');


  constructor(private cartService: CartService, private userService: UserService) { }

  ngOnInit(): void {
    this.initializeCart();
    this.getUserById(1);
  }

  initializeCart() {
    this.items = this.cartService.converToListFromMap();
    this.totalCart = this.cartService.totalCart();
  }

  getTotal(): number {
    return this.cartService.totalCart();
  }

  removeProduct(productId: number) {
    this.cartService.deleteItemCart(productId);
    this.initializeCart();
  }

  getUserById(id: number) {
    this.userService.getUserById(id).subscribe(user => {
      this.user = user;
    });
  }

  onPay() {
    throw new Error('Method not implemented.');
  }


}
