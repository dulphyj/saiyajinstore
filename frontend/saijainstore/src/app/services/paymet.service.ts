import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { DataPayment } from '../common/data-payment';
import { Observable } from 'rxjs';
import { UrlPaypalResponse } from '../common/url-paypal-response';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class PaymetService {

  private apiUrl = `${environment.apiUrl}/payments`;

  constructor(private httpClient: HttpClient, private headerService: HeaderService) { }

  getUrlPaypalPayment(DataPayment: DataPayment): Observable<UrlPaypalResponse> {
    return this.httpClient.post<UrlPaypalResponse>(this.apiUrl, DataPayment, { headers: this.headerService.headers });
  }
}
