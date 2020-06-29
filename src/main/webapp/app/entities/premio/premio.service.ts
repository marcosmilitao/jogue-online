import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPremio } from 'app/shared/model/premio.model';

type EntityResponseType = HttpResponse<IPremio>;
type EntityArrayResponseType = HttpResponse<IPremio[]>;

@Injectable({ providedIn: 'root' })
export class PremioService {
  public resourceUrl = SERVER_API_URL + 'api/premios';

  constructor(protected http: HttpClient) {}

  create(premio: IPremio): Observable<EntityResponseType> {
    return this.http.post<IPremio>(this.resourceUrl, premio, { observe: 'response' });
  }

  update(premio: IPremio): Observable<EntityResponseType> {
    return this.http.put<IPremio>(this.resourceUrl, premio, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPremio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPremio[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
