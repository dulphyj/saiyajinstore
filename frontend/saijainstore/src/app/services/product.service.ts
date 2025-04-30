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
  private apiUrl: string = `${environment.apiUrl}/products/admin`;
  private apiUrlUser: string = `${environment.apiUrl}/products/user`;
  private imageUrl: string = `${environment.apiUrl}/images/admin`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl, { headers: this.headerService.headers });
  }

  getAllProductsAsUser(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrlUser, { headers: this.headerService.headers });
  }

  createProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(`${this.apiUrl}`, product, { headers: this.headerService.headers });
  }

  deleteProduct(productId: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/${productId}`, { headers: this.headerService.headers });
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${id}`, { headers: this.headerService.headers });
  }

  getProductByIdAsUser(id: number): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrlUser}/${id}`, { headers: this.headerService.headers });
  }

  updateProduct(id: number, formData: FormData): Observable<Product> {
    return this.httpClient.put<Product>(`${this.apiUrl}/update/${id}`, formData, { headers: this.headerService.headers });
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.imageUrl, formData, { headers: this.headerService.headers });
  }

}
