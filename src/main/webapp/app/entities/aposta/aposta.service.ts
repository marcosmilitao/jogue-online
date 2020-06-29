import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAposta } from 'app/shared/model/aposta.model';

type EntityResponseType = HttpResponse<IAposta>;
type EntityArrayResponseType = HttpResponse<IAposta[]>;

@Injectable({ providedIn: 'root' })
export class ApostaService {
  public resourceUrl = SERVER_API_URL + 'api/apostas';

  constructor(protected http: HttpClient) {}

  create(aposta: IAposta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(aposta);
    return this.http
      .post<IAposta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(aposta: IAposta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(aposta);
    return this.http
      .put<IAposta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAposta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAposta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(aposta: IAposta): IAposta {
    const copy: IAposta = Object.assign({}, aposta, {
      dataAposta: aposta.dataAposta && aposta.dataAposta.isValid() ? aposta.dataAposta.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataAposta = res.body.dataAposta ? moment(res.body.dataAposta) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((aposta: IAposta) => {
        aposta.dataAposta = aposta.dataAposta ? moment(aposta.dataAposta) : undefined;
      });
    }
    return res;
  }
}
