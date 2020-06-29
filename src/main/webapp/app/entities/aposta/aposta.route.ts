import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAposta, Aposta } from 'app/shared/model/aposta.model';
import { ApostaService } from './aposta.service';
import { ApostaComponent } from './aposta.component';
import { ApostaDetailComponent } from './aposta-detail.component';
import { ApostaUpdateComponent } from './aposta-update.component';

@Injectable({ providedIn: 'root' })
export class ApostaResolve implements Resolve<IAposta> {
  constructor(private service: ApostaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAposta> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((aposta: HttpResponse<Aposta>) => {
          if (aposta.body) {
            return of(aposta.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Aposta());
  }
}

export const apostaRoute: Routes = [
  {
    path: '',
    component: ApostaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.aposta.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApostaDetailComponent,
    resolve: {
      aposta: ApostaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.aposta.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApostaUpdateComponent,
    resolve: {
      aposta: ApostaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.aposta.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApostaUpdateComponent,
    resolve: {
      aposta: ApostaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.aposta.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
