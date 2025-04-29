import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../common/user';
import { Observable } from 'rxjs';
import { Userdto } from '../common/userdto';
import { JwtClient } from '../common/jwt-client';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl: string = `${environment.apiUrl}/security`;

  constructor(private httpClient: HttpClient) { }

  register(user: User): Observable<User> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<User>(`${this.apiUrl}/register`, user, { headers });
  }

  login(userDto: Userdto): Observable<JwtClient> {
    return this.httpClient.post<JwtClient>(`${this.apiUrl}/login`, userDto);
  }
}
