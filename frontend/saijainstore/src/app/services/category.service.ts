import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../common/category';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private categoryUrl: string = `${environment.apiUrl}/admin/categories`;

  constructor(private httpClient: HttpClient) { }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.categoryUrl);
  }

  createCategory(category: Category): Observable<Category> {
    return this.httpClient.post<Category>(`${this.categoryUrl}`, category);
  }

  getCategoryById(id: number): Observable<Category> {
    return this.httpClient.get<Category>(`${this.categoryUrl}/${id}`);
  }

  deleteCategory(id: number): Observable<any> {
    return this.httpClient.delete(`${this.categoryUrl}/delete/${id}`)
  }
}