import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILoteria } from 'app/shared/model/loteria.model';

type EntityResponseType = HttpResponse<ILoteria>;
type EntityArrayResponseType = HttpResponse<ILoteria[]>;

@Injectable({ providedIn: 'root' })
export class LoteriaService {
  public resourceUrl = SERVER_API_URL + 'api/loterias';

  constructor(protected http: HttpClient) {}

  create(loteria: ILoteria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(loteria);
    return this.http
      .post<ILoteria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(loteria: ILoteria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(loteria);
    return this.http
      .put<ILoteria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILoteria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILoteria[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(loteria: ILoteria): ILoteria {
    const copy: ILoteria = Object.assign({}, loteria, {
      data: loteria.data && loteria.data.isValid() ? loteria.data.toJSON() : undefined
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
      res.body.forEach((loteria: ILoteria) => {
        loteria.data = loteria.data ? moment(loteria.data) : undefined;
      });
    }
    return res;
  }
}
