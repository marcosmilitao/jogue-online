import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBilhete } from 'app/shared/model/bilhete.model';

type EntityResponseType = HttpResponse<IBilhete>;
type EntityArrayResponseType = HttpResponse<IBilhete[]>;

@Injectable({ providedIn: 'root' })
export class BilheteService {
  public resourceUrl = SERVER_API_URL + 'api/bilhetes';

  constructor(protected http: HttpClient) {}

  create(bilhete: IBilhete): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bilhete);
    return this.http
      .post<IBilhete>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bilhete: IBilhete): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bilhete);
    return this.http
      .put<IBilhete>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBilhete>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBilhete[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bilhete: IBilhete): IBilhete {
    const copy: IBilhete = Object.assign({}, bilhete, {
      dataHoraAposta: bilhete.dataHoraAposta && bilhete.dataHoraAposta.isValid() ? bilhete.dataHoraAposta.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataHoraAposta = res.body.dataHoraAposta ? moment(res.body.dataHoraAposta) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bilhete: IBilhete) => {
        bilhete.dataHoraAposta = bilhete.dataHoraAposta ? moment(bilhete.dataHoraAposta) : undefined;
      });
    }
    return res;
  }
}
