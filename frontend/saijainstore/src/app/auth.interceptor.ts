import { HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { SessionStorageService } from "./services/session-storage.service";

export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn) {
  const sessionStorage = inject(SessionStorageService);
  const token = sessionStorage.getItem('token');
  if (token) {
    req = req.clone({
      headers: req.headers.set('Authorization', token),
    });
  }
  return next(req);
}