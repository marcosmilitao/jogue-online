import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';

type EntityResponseType = HttpResponse<IDiasFuncionamento>;
type EntityArrayResponseType = HttpResponse<IDiasFuncionamento[]>;

@Injectable({ providedIn: 'root' })
export class DiasFuncionamentoService {
  public resourceUrl = SERVER_API_URL + 'api/dias-funcionamentos';

  constructor(protected http: HttpClient) {}

  create(diasFuncionamento: IDiasFuncionamento): Observable<EntityResponseType> {
    return this.http.post<IDiasFuncionamento>(this.resourceUrl, diasFuncionamento, { observe: 'response' });
  }

  update(diasFuncionamento: IDiasFuncionamento): Observable<EntityResponseType> {
    return this.http.put<IDiasFuncionamento>(this.resourceUrl, diasFuncionamento, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDiasFuncionamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDiasFuncionamento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
