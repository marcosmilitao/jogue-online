import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDiasFuncionamento, DiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { DiasFuncionamentoService } from './dias-funcionamento.service';
import { DiasFuncionamentoComponent } from './dias-funcionamento.component';
import { DiasFuncionamentoDetailComponent } from './dias-funcionamento-detail.component';
import { DiasFuncionamentoUpdateComponent } from './dias-funcionamento-update.component';

@Injectable({ providedIn: 'root' })
export class DiasFuncionamentoResolve implements Resolve<IDiasFuncionamento> {
  constructor(private service: DiasFuncionamentoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDiasFuncionamento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((diasFuncionamento: HttpResponse<DiasFuncionamento>) => {
          if (diasFuncionamento.body) {
            return of(diasFuncionamento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DiasFuncionamento());
  }
}

export const diasFuncionamentoRoute: Routes = [
  {
    path: '',
    component: DiasFuncionamentoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.diasFuncionamento.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DiasFuncionamentoDetailComponent,
    resolve: {
      diasFuncionamento: DiasFuncionamentoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.diasFuncionamento.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DiasFuncionamentoUpdateComponent,
    resolve: {
      diasFuncionamento: DiasFuncionamentoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.diasFuncionamento.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DiasFuncionamentoUpdateComponent,
    resolve: {
      diasFuncionamento: DiasFuncionamentoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.diasFuncionamento.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
