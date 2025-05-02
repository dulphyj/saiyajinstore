import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Order } from '../common/order';
import { Observable } from 'rxjs';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderUrl = `${environment.apiUrl}/orders`;

  constructor(private httpClient: HttpClient) { }

  getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.orderUrl);
  }

  createOrder(order: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.orderUrl, order);
  }

  updateOrder(formData: FormData): Observable<any> {
    return this.httpClient.post<Order>(`${this.orderUrl}/update/state`, formData); //Observable any
  }

  getOrderByUsserId(id: number): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${this.orderUrl}/by-user/${id}`);
  }

  getOrderById(id: number): Observable<Order> {
    return this.httpClient.get<Order>(`${this.orderUrl}/${id}`);
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.orderUrl}/user/${id}`);
  }
}
