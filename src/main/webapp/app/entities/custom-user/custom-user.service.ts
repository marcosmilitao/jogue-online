import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomUser } from 'app/shared/model/custom-user.model';

type EntityResponseType = HttpResponse<ICustomUser>;
type EntityArrayResponseType = HttpResponse<ICustomUser[]>;

@Injectable({ providedIn: 'root' })
export class CustomUserService {
  public resourceUrl = SERVER_API_URL + 'api/custom-users';

  constructor(protected http: HttpClient) {}

  create(customUser: ICustomUser): Observable<EntityResponseType> {
    return this.http.post<ICustomUser>(this.resourceUrl, customUser, { observe: 'response' });
  }

  update(customUser: ICustomUser): Observable<EntityResponseType> {
    return this.http.put<ICustomUser>(this.resourceUrl, customUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
