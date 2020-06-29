import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILoteria, Loteria } from 'app/shared/model/loteria.model';
import { LoteriaService } from './loteria.service';
import { LoteriaComponent } from './loteria.component';
import { LoteriaDetailComponent } from './loteria-detail.component';
import { LoteriaUpdateComponent } from './loteria-update.component';

@Injectable({ providedIn: 'root' })
export class LoteriaResolve implements Resolve<ILoteria> {
  constructor(private service: LoteriaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILoteria> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((loteria: HttpResponse<Loteria>) => {
          if (loteria.body) {
            return of(loteria.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Loteria());
  }
}

export const loteriaRoute: Routes = [
  {
    path: '',
    component: LoteriaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.loteria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LoteriaDetailComponent,
    resolve: {
      loteria: LoteriaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.loteria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LoteriaUpdateComponent,
    resolve: {
      loteria: LoteriaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.loteria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LoteriaUpdateComponent,
    resolve: {
      loteria: LoteriaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.loteria.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
