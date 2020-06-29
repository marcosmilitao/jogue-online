import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILimiteVenda } from 'app/shared/model/limite-venda.model';

type EntityResponseType = HttpResponse<ILimiteVenda>;
type EntityArrayResponseType = HttpResponse<ILimiteVenda[]>;

@Injectable({ providedIn: 'root' })
export class LimiteVendaService {
  public resourceUrl = SERVER_API_URL + 'api/limite-vendas';

  constructor(protected http: HttpClient) {}

  create(limiteVenda: ILimiteVenda): Observable<EntityResponseType> {
    return this.http.post<ILimiteVenda>(this.resourceUrl, limiteVenda, { observe: 'response' });
  }

  update(limiteVenda: ILimiteVenda): Observable<EntityResponseType> {
    return this.http.put<ILimiteVenda>(this.resourceUrl, limiteVenda, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILimiteVenda>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILimiteVenda[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
