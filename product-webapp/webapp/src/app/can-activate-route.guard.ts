import { CanActivateFn } from '@angular/router';
import { SharedDataService } from './services/shared-data.service';
import { inject } from '@angular/core';
import { RouterService } from './router.service';

export const canActivateRouteGuard: CanActivateFn = (route, state) => {
  const isLoggedIn = inject(SharedDataService);
  const router = inject(RouterService)
  return new Promise<boolean>((resolve, reject) => {
    if(isLoggedIn.getToken() === null || isLoggedIn.getToken() === ''){
      reject(false);
      console.log(isLoggedIn.getToken(), '- token')
      router.routeToLogin();
    } else {  
      resolve(true);
    }
  });
  
};
