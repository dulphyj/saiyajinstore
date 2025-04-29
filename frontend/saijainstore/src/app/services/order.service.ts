import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Order } from '../common/order';
import { Observable } from 'rxjs';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderUrl = `${environment.apiUrl}/orders`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getOrders() {
    return this.httpClient.get(this.orderUrl);
  }

  createOrder(order: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.orderUrl, order, { headers: this.headerService.headers });
  }

  updateOrder(formData: FormData): Observable<any> {
    return this.httpClient.post<Order>(`${this.orderUrl}/update/state`, formData, { headers: this.headerService.headers }); //Observable any
  }

  getOrderByUsserId(id: number): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${this.orderUrl}/by-user/${id}`, { headers: this.headerService.headers });
  }

  getOrderById(id: number): Observable<Order> {
    return this.httpClient.get<Order>(`${this.orderUrl}/${id}`, { headers: this.headerService.headers });
  }
}
