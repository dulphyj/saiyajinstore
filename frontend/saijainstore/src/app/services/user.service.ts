import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl: string = `${environment.apiUrl}/admin/users`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`, { headers: this.headerService.headers })
  }

  createUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.apiUrl}`, user, { headers: this.headerService.headers });
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl, { headers: this.headerService.headers });
  }

  deleteUser(id: number): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/delete/${id}`, { headers: this.headerService.headers });
  }


}
