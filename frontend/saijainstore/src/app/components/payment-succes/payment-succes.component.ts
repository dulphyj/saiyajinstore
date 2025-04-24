import { Component, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../header-user/header-user.component";
import { OrderService } from '../../services/order.service';
import { SessionStorageService } from '../../services/session-storage.service';
import { OrderState } from '../../common/order-state';

@Component({
  selector: 'app-payment-succes',
  imports: [HeaderUserComponent],
  templateUrl: './payment-succes.component.html',
  styleUrl: './payment-succes.component.css'
})
export class PaymentSuccesComponent implements OnInit {

  constructor(private orderService: OrderService, private sessionStorage: SessionStorageService) { }

  ngOnInit(): void {
    console.log(this.sessionStorage.getItem('order'));
    let order = this.sessionStorage.getItem('order');
    let formData = new FormData();
    formData.append('id', order.id);
    formData.append('state', OrderState.CONFIRMED.toString());

    this.orderService.updateOrder(formData).subscribe(
      data => {
        console.log('Order updated successfully', data);

        //this.sessionStorage.removeItem('order');

      }
    )
  }

}
