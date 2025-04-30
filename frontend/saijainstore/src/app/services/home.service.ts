import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  apiUrl: string = `${environment.apiUrl}/home`;

  constructor(private httpClient: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }
}
