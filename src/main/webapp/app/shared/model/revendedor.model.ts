import { Moment } from 'moment';
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
  saldo?: number;
  senha?: string;
  data?: Moment;
  comissao?: number;
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
    public saldo?: number,
    public senha?: string,
    public data?: Moment,
    public comissao?: number,
    public terminals?: ITerminal[],
    public promotor?: IPromotor
  ) {
    this.situacao = this.situacao || false;
  }
}
