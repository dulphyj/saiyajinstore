import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl: string = `${environment.apiUrl}/products/admin`;
  private apiUrlUser: string = `${environment.apiUrl}/products/user`;
  private imageUrl: string = `${environment.apiUrl}/images/admin`;

  constructor(private httpClient: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }

  getAllProductsAsUser(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrlUser);
  }

  createProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(`${this.apiUrl}`, product);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/${productId}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${id}`);
  }

  getProductByIdAsUser(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrlUser}/${id}`);
  }

  updateProduct(id: number, formData: FormData): Observable<Product> {
    return this.httpClient.put<Product>(`${this.apiUrl}/update/${id}`, formData);
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.imageUrl, formData);
  }

}
