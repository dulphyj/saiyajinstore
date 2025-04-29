import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class CloudinaryService {

  private apiUrl: string = `${environment.apiUrl}/admin/images`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  uploadImage(file: File): Observable<{ url: string }> {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<{ url: string }>(this.apiUrl, formData, { headers: this.headerService.headers });
  }

  deleteImage(publicId: string): Observable<{ message: string }> {
    return this.httpClient.delete<{ message: string }>(`${this.apiUrl}/${publicId}`, { headers: this.headerService.headers });
  }
}
