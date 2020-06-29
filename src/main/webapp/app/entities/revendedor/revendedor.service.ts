import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRevendedor } from 'app/shared/model/revendedor.model';

type EntityResponseType = HttpResponse<IRevendedor>;
type EntityArrayResponseType = HttpResponse<IRevendedor[]>;

@Injectable({ providedIn: 'root' })
export class RevendedorService {
  public resourceUrl = SERVER_API_URL + 'api/revendedors';

  constructor(protected http: HttpClient) {}

  create(revendedor: IRevendedor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(revendedor);
    return this.http
      .post<IRevendedor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(revendedor: IRevendedor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(revendedor);
    return this.http
      .put<IRevendedor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRevendedor>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRevendedor[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(revendedor: IRevendedor): IRevendedor {
    const copy: IRevendedor = Object.assign({}, revendedor, {
      data: revendedor.data && revendedor.data.isValid() ? revendedor.data.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((revendedor: IRevendedor) => {
        revendedor.data = revendedor.data ? moment(revendedor.data) : undefined;
      });
    }
    return res;
  }
}
