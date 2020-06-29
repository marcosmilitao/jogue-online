import { Moment } from 'moment';
import { IBanca } from 'app/shared/model/banca.model';

export interface ITerminal {
  id?: number;
  codigoTerminal?: number;
  telefoneChipe?: number;
  revendedor?: string;
  serialChip?: string;
  menssagem?: string;
  senhaComunicacao?: string;
  dataInicio?: Moment;
  situacao?: boolean;
  versaoTerminal?: string;
  mudaCodigo?: number;
  numeroTelefoneProvedor?: number;
  dataEntrada?: Moment;
  numeroFonte?: number;
  codigoAutorizacao?: number;
  serialTerminal?: string;
  email?: string;
  codigoBanca?: number;
  banca?: IBanca;
}

export class Terminal implements ITerminal {
  constructor(
    public id?: number,
    public codigoTerminal?: number,
    public telefoneChipe?: number,
    public revendedor?: string,
    public serialChip?: string,
    public menssagem?: string,
    public senhaComunicacao?: string,
    public dataInicio?: Moment,
    public situacao?: boolean,
    public versaoTerminal?: string,
    public mudaCodigo?: number,
    public numeroTelefoneProvedor?: number,
    public dataEntrada?: Moment,
    public numeroFonte?: number,
    public codigoAutorizacao?: number,
    public serialTerminal?: string,
    public email?: string,
    public codigoBanca?: number,
    public banca?: IBanca
  ) {
    this.situacao = this.situacao || false;
  }
}
