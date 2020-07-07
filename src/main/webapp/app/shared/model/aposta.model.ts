import { Moment } from 'moment';
import { IBilhete } from 'app/shared/model/bilhete.model';

export interface IAposta {
  id?: number;
  numeroBilhete?: number;
  dataAposta?: Moment;
  loteriaCodigo?: number;
  modalide?: string;
  codigoModalidade?: string;
  premio?: string;
  codigoPremio?: number;
  valorJogo?: number;
  codigoBanca?: number;
  numeroAposta?: number;
  bilhete?: IBilhete;
}

export class Aposta implements IAposta {
  constructor(
    public id?: number,
    public numeroBilhete?: number,
    public dataAposta?: Moment,
    public loteriaCodigo?: number,
    public modalide?: string,
    public codigoModalidade?: string,
    public premio?: string,
    public codigoPremio?: number,
    public valorJogo?: number,
    public codigoBanca?: number,
    public numeroAposta?: number,
    public bilhete?: IBilhete
  ) {}
}
