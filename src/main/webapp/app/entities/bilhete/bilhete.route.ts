import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBilhete, Bilhete } from 'app/shared/model/bilhete.model';
import { BilheteService } from './bilhete.service';
import { BilheteComponent } from './bilhete.component';
import { BilheteDetailComponent } from './bilhete-detail.component';
import { BilheteUpdateComponent } from './bilhete-update.component';

@Injectable({ providedIn: 'root' })
export class BilheteResolve implements Resolve<IBilhete> {
  constructor(private service: BilheteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBilhete> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bilhete: HttpResponse<Bilhete>) => {
          if (bilhete.body) {
            return of(bilhete.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bilhete());
  }
}

export const bilheteRoute: Routes = [
  {
    path: '',
    component: BilheteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.bilhete.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BilheteDetailComponent,
    resolve: {
      bilhete: BilheteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.bilhete.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BilheteUpdateComponent,
    resolve: {
      bilhete: BilheteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.bilhete.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BilheteUpdateComponent,
    resolve: {
      bilhete: BilheteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.bilhete.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
