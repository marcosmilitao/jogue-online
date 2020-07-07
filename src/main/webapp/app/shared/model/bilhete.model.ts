import { Moment } from 'moment';
import { IAposta } from 'app/shared/model/aposta.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface IBilhete {
  id?: number;
  numeroBilhete?: number;
  codigoBanca?: number;
  codigoLoteria?: number;
  loteriaNome?: string;
  valorTotalAposta?: number;
  bonusBanca?: number;
  bonusIndividual?: number;
  comicao?: number;
  valorBilhete?: number;
  dataHoraAposta?: Moment;
  qrcode?: string;
  codigoTerminal?: number;
  apostas?: IAposta[];
  banca?: IBanca;
}

export class Bilhete implements IBilhete {
  constructor(
    public id?: number,
    public numeroBilhete?: number,
    public codigoBanca?: number,
    public codigoLoteria?: number,
    public loteriaNome?: string,
    public valorTotalAposta?: number,
    public bonusBanca?: number,
    public bonusIndividual?: number,
    public comicao?: number,
    public valorBilhete?: number,
    public dataHoraAposta?: Moment,
    public qrcode?: string,
    public codigoTerminal?: number,
    public apostas?: IAposta[],
    public banca?: IBanca
  ) {}
}
