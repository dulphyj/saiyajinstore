import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../services/session-storage.service';

export const authAdminGuard: CanActivateFn = (route, state) => {
  const session = inject(SessionStorageService);
  const router = inject(Router);

  const token = session.getItem('token');
  const type = session.getItem('userType');

  if (token && type === 'ADMIN') {
    return true;
  }
  return router.createUrlTree(['/error']);
};
