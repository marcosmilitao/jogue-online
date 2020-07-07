import { Moment } from 'moment';
import { IRevendedor } from 'app/shared/model/revendedor.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface IPromotor {
  id?: number;
  nome?: string;
  cidade?: string;
  estado?: string;
  telefone?: string;
  comissao?: number;
  data?: Moment;
  revendedors?: IRevendedor[];
  banca?: IBanca;
}

export class Promotor implements IPromotor {
  constructor(
    public id?: number,
    public nome?: string,
    public cidade?: string,
    public estado?: string,
    public telefone?: string,
    public comissao?: number,
    public data?: Moment,
    public revendedors?: IRevendedor[],
    public banca?: IBanca
  ) {}
}
