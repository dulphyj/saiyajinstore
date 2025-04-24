import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() { }

  setItem(key: string, value: any) {
    sessionStorage.setItem(key, JSON.stringify(value));
  }

  getItem(key: string) {
    const value = sessionStorage.getItem(key);
    return value ? JSON.parse(value) : null;
  }

  removeItem(key: string) {
    sessionStorage.removeItem(key);
  }

  clear() {
    sessionStorage.clear();
  }
}
