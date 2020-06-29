import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBanca, Banca } from 'app/shared/model/banca.model';
import { BancaService } from './banca.service';
import { BancaComponent } from './banca.component';
import { BancaDetailComponent } from './banca-detail.component';
import { BancaUpdateComponent } from './banca-update.component';

@Injectable({ providedIn: 'root' })
export class BancaResolve implements Resolve<IBanca> {
  constructor(private service: BancaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBanca> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((banca: HttpResponse<Banca>) => {
          if (banca.body) {
            return of(banca.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Banca());
  }
}

export const bancaRoute: Routes = [
  {
    path: '',
    component: BancaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.banca.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BancaDetailComponent,
    resolve: {
      banca: BancaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.banca.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BancaUpdateComponent,
    resolve: {
      banca: BancaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.banca.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BancaUpdateComponent,
    resolve: {
      banca: BancaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.banca.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
