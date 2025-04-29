import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../common/category';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private categoryUrl: string = `${environment.apiUrl}/admin/categories`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.categoryUrl, { headers: this.headerService.headers });
  }

  createCategory(category: Category): Observable<Category> {
    return this.httpClient.post<Category>(`${this.categoryUrl}`, category, { headers: this.headerService.headers });
  }

  getCategoryById(id: number): Observable<Category> {
    return this.httpClient.get<Category>(`${this.categoryUrl}/${id}`, { headers: this.headerService.headers });
  }

  deleteCategory(id: number): Observable<any> {
    return this.httpClient.delete(`${this.categoryUrl}/delete/${id}`, { headers: this.headerService.headers })
  }
}