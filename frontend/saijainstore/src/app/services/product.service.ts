import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl: string = "http://localhost:8080/api/products";

  constructor(private httpeClient: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpeClient.get<Product[]>(this.apiUrl);
  }

  createProduct(formData: any): Observable<any> {
    return this.httpeClient.post<any>(`${this.apiUrl}/admin`, formData);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.httpeClient.delete(`${this.apiUrl}/admin/${productId}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.httpeClient.get<Product>(`${this.apiUrl}/${id}`);
  }
}
