import { IUser } from 'app/core/user/user.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface ICustomUser {
  id?: number;
  login?: string;
  banca?: string;
  user?: IUser;
  bancas?: IBanca[];
}

export class CustomUser implements ICustomUser {
  constructor(public id?: number, public login?: string, public banca?: string, public user?: IUser, public bancas?: IBanca[]) {}
}
