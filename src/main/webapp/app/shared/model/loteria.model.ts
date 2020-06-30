import { Moment } from 'moment';
import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { IPremio } from 'app/shared/model/premio.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface ILoteria {
  id?: number;
  nome?: string;
  horaEncerramento?: string;
  premiacaoInicio?: string;
  status?: boolean;
  limitePremio?: number;
  data?: Moment;
  codigo?: number;
  descricao?: string;
  hora?: number;
  minuto?: number;
  disponivel?: boolean;
  descricaoCompleta?: string;
  diasFuncionamentos?: IDiasFuncionamento[];
  premios?: IPremio[];
  bancas?: IBanca[];
}

export class Loteria implements ILoteria {
  constructor(
    public id?: number,
    public nome?: string,
    public horaEncerramento?: string,
    public premiacaoInicio?: string,
    public status?: boolean,
    public limitePremio?: number,
    public data?: Moment,
    public codigo?: number,
    public descricao?: string,
    public hora?: number,
    public minuto?: number,
    public disponivel?: boolean,
    public descricaoCompleta?: string,
    public diasFuncionamentos?: IDiasFuncionamento[],
    public premios?: IPremio[],
    public bancas?: IBanca[]
  ) {
    this.status = this.status || false;
    this.disponivel = this.disponivel || false;
  }
}
