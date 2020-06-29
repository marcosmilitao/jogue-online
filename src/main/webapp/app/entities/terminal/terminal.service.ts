import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITerminal } from 'app/shared/model/terminal.model';

type EntityResponseType = HttpResponse<ITerminal>;
type EntityArrayResponseType = HttpResponse<ITerminal[]>;

@Injectable({ providedIn: 'root' })
export class TerminalService {
  public resourceUrl = SERVER_API_URL + 'api/terminals';

  constructor(protected http: HttpClient) {}

  create(terminal: ITerminal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(terminal);
    return this.http
      .post<ITerminal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(terminal: ITerminal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(terminal);
    return this.http
      .put<ITerminal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITerminal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITerminal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(terminal: ITerminal): ITerminal {
    const copy: ITerminal = Object.assign({}, terminal, {
      dataInicio: terminal.dataInicio && terminal.dataInicio.isValid() ? terminal.dataInicio.toJSON() : undefined,
      dataEntrada: terminal.dataEntrada && terminal.dataEntrada.isValid() ? terminal.dataEntrada.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataInicio = res.body.dataInicio ? moment(res.body.dataInicio) : undefined;
      res.body.dataEntrada = res.body.dataEntrada ? moment(res.body.dataEntrada) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((terminal: ITerminal) => {
        terminal.dataInicio = terminal.dataInicio ? moment(terminal.dataInicio) : undefined;
        terminal.dataEntrada = terminal.dataEntrada ? moment(terminal.dataEntrada) : undefined;
      });
    }
    return res;
  }
}
