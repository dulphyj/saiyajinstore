import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CloudinaryService {

  private apiUrl = 'http://localhost:8080/api/images/admin'; // Ajusta si es necesario

  constructor(private httpClient: HttpClient) { }

  uploadImage(file: File): Observable<{ url: string }> {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.apiUrl, formData);
  }

  deleteImage(publicId: string): Observable<{ message: string }> {
    return this.httpClient.delete<{ message: string }>(`${this.apiUrl}/${publicId}`);
  }
}
