import { Moment } from 'moment';
import { ISaldo } from 'app/shared/model/saldo.model';
import { ITerminal } from 'app/shared/model/terminal.model';
import { IPromotor } from 'app/shared/model/promotor.model';

export interface IRevendedor {
  id?: number;
  nome?: string;
  cidade?: string;
  estado?: string;
  telefone?: string;
  tipo?: string;
  tipoColetor?: string;
  serialColetor?: string;
  nomeComercial?: string;
  situacao?: boolean;
  senha?: string;
  data?: Moment;
  comissao?: number;
  saldo?: ISaldo;
  terminals?: ITerminal[];
  promotor?: IPromotor;
}

export class Revendedor implements IRevendedor {
  constructor(
    public id?: number,
    public nome?: string,
    public cidade?: string,
    public estado?: string,
    public telefone?: string,
    public tipo?: string,
    public tipoColetor?: string,
    public serialColetor?: string,
    public nomeComercial?: string,
    public situacao?: boolean,
    public senha?: string,
    public data?: Moment,
    public comissao?: number,
    public saldo?: ISaldo,
    public terminals?: ITerminal[],
    public promotor?: IPromotor
  ) {
    this.situacao = this.situacao || false;
  }
}
