import { Moment } from 'moment';
import { IMovimentacao } from 'app/shared/model/movimentacao.model';

export interface ISaldo {
  id?: number;
  valor?: number;
  dataAtualizacao?: Moment;
  movimentacaos?: IMovimentacao[];
}

export class Saldo implements ISaldo {
  constructor(public id?: number, public valor?: number, public dataAtualizacao?: Moment, public movimentacaos?: IMovimentacao[]) {}
}
