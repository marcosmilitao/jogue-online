import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILimiteVenda, LimiteVenda } from 'app/shared/model/limite-venda.model';
import { LimiteVendaService } from './limite-venda.service';
import { LimiteVendaComponent } from './limite-venda.component';
import { LimiteVendaDetailComponent } from './limite-venda-detail.component';
import { LimiteVendaUpdateComponent } from './limite-venda-update.component';

@Injectable({ providedIn: 'root' })
export class LimiteVendaResolve implements Resolve<ILimiteVenda> {
  constructor(private service: LimiteVendaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILimiteVenda> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((limiteVenda: HttpResponse<LimiteVenda>) => {
          if (limiteVenda.body) {
            return of(limiteVenda.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LimiteVenda());
  }
}

export const limiteVendaRoute: Routes = [
  {
    path: '',
    component: LimiteVendaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.limiteVenda.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LimiteVendaDetailComponent,
    resolve: {
      limiteVenda: LimiteVendaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.limiteVenda.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LimiteVendaUpdateComponent,
    resolve: {
      limiteVenda: LimiteVendaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.limiteVenda.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LimiteVendaUpdateComponent,
    resolve: {
      limiteVenda: LimiteVendaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.limiteVenda.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
