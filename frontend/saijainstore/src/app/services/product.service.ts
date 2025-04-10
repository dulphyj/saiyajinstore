import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl: string = `${environment.apiUrl}/products`;
  private imageUrl: string = `${environment.apiUrl}/images/admin`;

  constructor(private httpClient: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }

  createProduct(product: Product): Observable<Product> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<Product>(`${this.apiUrl}/admin`, product, { headers });
  }

  deleteProduct(productId: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/admin/${productId}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${id}`);
  }

  updateProduct(id: number, formData: FormData): Observable<Product> {
    return this.httpClient.put<Product>(`${this.apiUrl}/admin/update/${id}`, formData);
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.imageUrl, formData);
  }

}
