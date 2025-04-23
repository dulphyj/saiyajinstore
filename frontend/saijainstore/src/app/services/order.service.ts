import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Order } from '../common/order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderUrl = `${environment.apiUrl}/orders`;

  constructor(private httpClient: HttpClient) { }

  getOrders() {
    return this.httpClient.get(this.orderUrl);
  }

  createOrder(order: Order): Observable<Order> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<Order>(this.orderUrl, order);
  }

  updateOrder(id: number, formData: FormData): Observable<Order> {
    return this.httpClient.put<Order>(`${this.orderUrl}/update/state${id}`, formData); //Observable any
  }

  getOrderByUsserId(id: number): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${this.orderUrl}/by-user/${id}`);
  }

  getOrderById(id: number): Observable<Order> {
    return this.httpClient.get<Order>(`${this.orderUrl}/${id}`);
  }
}
