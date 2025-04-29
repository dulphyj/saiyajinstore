import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { environment } from '../../environments/environment';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl: string = `${environment.apiUrl}/products`;
  private imageUrl: string = `${environment.apiUrl}/images/admin`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl, { headers: this.headerService.headers });
  }

  createProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(`${this.apiUrl}/admin`, product, { headers: this.headerService.headers });
  }

  deleteProduct(productId: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/admin/${productId}`, { headers: this.headerService.headers });
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${id}`, { headers: this.headerService.headers });
  }

  updateProduct(id: number, formData: FormData): Observable<Product> {
    return this.httpClient.put<Product>(`${this.apiUrl}/admin/update/${id}`, formData, { headers: this.headerService.headers });
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.imageUrl, formData, { headers: this.headerService.headers });
  }

}
