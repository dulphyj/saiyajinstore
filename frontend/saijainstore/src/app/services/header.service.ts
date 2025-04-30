import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  public headers: HttpHeaders = new HttpHeaders;

  constructor(private sessionStorage: SessionStorageService) {
    const userToken = this.sessionStorage.getItem('token');
    const token = userToken?.token || '';
    this.headers = new HttpHeaders({
      'Authorization': `${token}`
    });
  }
}
