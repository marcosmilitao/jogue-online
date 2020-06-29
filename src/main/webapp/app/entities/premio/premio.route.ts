import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPremio, Premio } from 'app/shared/model/premio.model';
import { PremioService } from './premio.service';
import { PremioComponent } from './premio.component';
import { PremioDetailComponent } from './premio-detail.component';
import { PremioUpdateComponent } from './premio-update.component';

@Injectable({ providedIn: 'root' })
export class PremioResolve implements Resolve<IPremio> {
  constructor(private service: PremioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPremio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((premio: HttpResponse<Premio>) => {
          if (premio.body) {
            return of(premio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Premio());
  }
}

export const premioRoute: Routes = [
  {
    path: '',
    component: PremioComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.premio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PremioDetailComponent,
    resolve: {
      premio: PremioResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.premio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PremioUpdateComponent,
    resolve: {
      premio: PremioResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.premio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PremioUpdateComponent,
    resolve: {
      premio: PremioResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.premio.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
