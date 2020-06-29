import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRevendedor, Revendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from './revendedor.service';
import { RevendedorComponent } from './revendedor.component';
import { RevendedorDetailComponent } from './revendedor-detail.component';
import { RevendedorUpdateComponent } from './revendedor-update.component';

@Injectable({ providedIn: 'root' })
export class RevendedorResolve implements Resolve<IRevendedor> {
  constructor(private service: RevendedorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRevendedor> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((revendedor: HttpResponse<Revendedor>) => {
          if (revendedor.body) {
            return of(revendedor.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Revendedor());
  }
}

export const revendedorRoute: Routes = [
  {
    path: '',
    component: RevendedorComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.revendedor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RevendedorDetailComponent,
    resolve: {
      revendedor: RevendedorResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.revendedor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RevendedorUpdateComponent,
    resolve: {
      revendedor: RevendedorResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.revendedor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RevendedorUpdateComponent,
    resolve: {
      revendedor: RevendedorResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.revendedor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
