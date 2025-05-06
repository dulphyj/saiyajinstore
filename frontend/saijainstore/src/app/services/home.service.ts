import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Category } from '../common/category';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  apiUrl: string = `${environment.apiUrl}/home`;

  constructor(private httpClient: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(`${this.apiUrl}/categories`);
  }

  searchProducts(name?: string, price?: number, categoryId?: number): Observable<Product[]> {
    let params: any = {};
    if (name) {
      params.name = name;
    }
    if (price != null) {
      params.price = price;
    }
    if (categoryId) {
      params.categoryId = categoryId;

    }
    return this.httpClient.get<Product[]>(`${this.apiUrl}/search`, { params });
  }
}
