import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModalidade, Modalidade } from 'app/shared/model/modalidade.model';
import { ModalidadeService } from './modalidade.service';
import { ModalidadeComponent } from './modalidade.component';
import { ModalidadeDetailComponent } from './modalidade-detail.component';
import { ModalidadeUpdateComponent } from './modalidade-update.component';

@Injectable({ providedIn: 'root' })
export class ModalidadeResolve implements Resolve<IModalidade> {
  constructor(private service: ModalidadeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModalidade> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modalidade: HttpResponse<Modalidade>) => {
          if (modalidade.body) {
            return of(modalidade.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Modalidade());
  }
}

export const modalidadeRoute: Routes = [
  {
    path: '',
    component: ModalidadeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.modalidade.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModalidadeDetailComponent,
    resolve: {
      modalidade: ModalidadeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.modalidade.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModalidadeUpdateComponent,
    resolve: {
      modalidade: ModalidadeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.modalidade.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModalidadeUpdateComponent,
    resolve: {
      modalidade: ModalidadeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jogueOnlineApp.modalidade.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
