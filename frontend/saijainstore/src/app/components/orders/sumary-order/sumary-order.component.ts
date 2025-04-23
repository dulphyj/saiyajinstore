import { Component, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { ItemCart } from '../../../common/item-cart';
import { User } from '../../../common/user';
import { CartService } from '../../../services/cart.service';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { OrderProduct } from '../../../common/order-product';
import { Order } from '../../../common/order';
import { OrderState } from '../../../common/order-state';
import { OrderService } from '../../../services/order.service';
import { PaymetService } from '../../../services/paymet.service';
import { DataPayment } from '../../../common/data-payment';

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
  orderProducts: OrderProduct[] = [];
  userId = 1;


  constructor(private cartService: CartService, private userService: UserService, private orderService: OrderService, private paymentService: PaymetService) { }

  ngOnInit(): void {
    this.initializeCart();
    this.getUserById(this.userId);
  }

  initializeCart() {
    this.items = this.cartService.converToListFromMap();
    this.totalCart = this.cartService.totalCart();
  }

  generateOrder() {
    this.items.forEach(item => {
      this.orderProducts.push(new OrderProduct(null, item.id, item.quantity, item.price));
    });
    const order = new Order(null, new Date(), this.orderProducts, this.userId, OrderState.CANCELLED);
    this.orderService.createOrder(order).subscribe(
      data => {
        console.log('Order created successfully', data);
      }
    )
    //payment
    let urlPayment;
    let dataPayment = new DataPayment('PAYPAL', this.totalCart.toFixed(2), 'USD', 'COMPRA');
    this.paymentService.getUrlPaypalPayment(dataPayment).subscribe(
      data => {
        urlPayment = data.url;
        console.log('URL de pago:', urlPayment);
        window.location.href = urlPayment;
      }
    )
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

}
