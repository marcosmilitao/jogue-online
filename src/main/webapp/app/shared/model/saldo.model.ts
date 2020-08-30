import { Moment } from 'moment';
import { IMovimentacao } from 'app/shared/model/movimentacao.model';
import { IRevendedor } from 'app/shared/model/revendedor.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface ISaldo {
  id?: number;
  valor?: number;
  dataAtualizacao?: Moment;
  movimentacaos?: IMovimentacao[];
  revendedor?: IRevendedor;
  banca?: IBanca;
}

export class Saldo implements ISaldo {
  constructor(
    public id?: number,
    public valor?: number,
    public dataAtualizacao?: Moment,
    public movimentacaos?: IMovimentacao[],
    public revendedor?: IRevendedor,
    public banca?: IBanca
  ) {}
}
