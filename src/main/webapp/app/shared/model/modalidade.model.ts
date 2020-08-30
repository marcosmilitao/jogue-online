import { IPremio } from 'app/shared/model/premio.model';
import { IBanca } from 'app/shared/model/banca.model';

export interface IModalidade {
  id?: number;
  codigoModalidade?: string;
  nome?: string;
  menorPalpite?: number;
  maiorPalpite?: number;
  qtdePalpites?: number;
  qtdeMinimaPalpites?: number;
  qtdeCaracteres?: number;
  qtdeMinimaCaracteres?: number;
  menorValor?: number;
  maiorValor?: number;
  maiorValorExcessao?: boolean;
  repeticao?: boolean;
  mascara?: string;
  palpiteMultiplo?: boolean;
  palpiteMultiploTerminal?: boolean;
  ordenar?: boolean;
  permitePalpiteAleatorio?: boolean;
  premios?: IPremio[];
  bancas?: IBanca[];
}

export class Modalidade implements IModalidade {
  constructor(
    public id?: number,
    public codigoModalidade?: string,
    public nome?: string,
    public menorPalpite?: number,
    public maiorPalpite?: number,
    public qtdePalpites?: number,
    public qtdeMinimaPalpites?: number,
    public qtdeCaracteres?: number,
    public qtdeMinimaCaracteres?: number,
    public menorValor?: number,
    public maiorValor?: number,
    public maiorValorExcessao?: boolean,
    public repeticao?: boolean,
    public mascara?: string,
    public palpiteMultiplo?: boolean,
    public palpiteMultiploTerminal?: boolean,
    public ordenar?: boolean,
    public permitePalpiteAleatorio?: boolean,
    public premios?: IPremio[],
    public bancas?: IBanca[]
  ) {
    this.maiorValorExcessao = this.maiorValorExcessao || false;
    this.repeticao = this.repeticao || false;
    this.palpiteMultiplo = this.palpiteMultiplo || false;
    this.palpiteMultiploTerminal = this.palpiteMultiploTerminal || false;
    this.ordenar = this.ordenar || false;
    this.permitePalpiteAleatorio = this.permitePalpiteAleatorio || false;
  }
}
