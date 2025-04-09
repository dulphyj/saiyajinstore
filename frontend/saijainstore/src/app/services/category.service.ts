import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../common/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private categoryUrl: string = "http://localhost:8080/api/admin/categories";

  constructor(private httpClient: HttpClient) { }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.categoryUrl);
  }

  createCategory(category: Category): Observable<Category> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.httpClient.post<Category>(`${this.categoryUrl}`, category, { headers })
  }

  getCategoryById(id: number): Observable<Category> {
    return this.httpClient.get<Category>(`${this.categoryUrl}/${id}`);
  }

  deleteCategory(id: number): Observable<any> {
    return this.httpClient.delete(`${this.categoryUrl}/delete/${id}`)
  }
}