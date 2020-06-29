import { Moment } from 'moment';
import { IBanca } from 'app/shared/model/banca.model';

export interface IAposta {
  id?: number;
  codigoJogo?: number;
  dataAposta?: Moment;
  loteriaNome?: string;
  loteriaCodigo?: number;
  modalide?: string;
  codigoModalidade?: string;
  premio?: string;
  codigoPremio?: number;
  valorJogo?: number;
  codigoBanca?: number;
  banca?: IBanca;
}

export class Aposta implements IAposta {
  constructor(
    public id?: number,
    public codigoJogo?: number,
    public dataAposta?: Moment,
    public loteriaNome?: string,
    public loteriaCodigo?: number,
    public modalide?: string,
    public codigoModalidade?: string,
    public premio?: string,
    public codigoPremio?: number,
    public valorJogo?: number,
    public codigoBanca?: number,
    public banca?: IBanca
  ) {}
}
