import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl: string = `${environment.apiUrl}/admin/users`;

  constructor(private httpClient: HttpClient) { }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`)
  }

  createUser(user: User): Observable<User> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<User>(`${this.apiUrl}`, user, { headers });
  }

}
