import { Moment } from 'moment';
import { IRevendedor } from 'app/shared/model/revendedor.model';

export interface ITerminal {
  id?: number;
  telefoneChipe?: number;
  serial?: string;
  menssagem?: string;
  senhaComunicacao?: string;
  dataInicio?: Moment;
  situacao?: boolean;
  versaoTerminal?: string;
  mudaCodigo?: number;
  dataEntrada?: Moment;
  numeroFonte?: number;
  codigoAutorizacao?: number;
  imei?: string;
  email?: string;
  codigoBanca?: number;
  revendedor?: IRevendedor;
}

export class Terminal implements ITerminal {
  constructor(
    public id?: number,
    public telefoneChipe?: number,
    public serial?: string,
    public menssagem?: string,
    public senhaComunicacao?: string,
    public dataInicio?: Moment,
    public situacao?: boolean,
    public versaoTerminal?: string,
    public mudaCodigo?: number,
    public dataEntrada?: Moment,
    public numeroFonte?: number,
    public codigoAutorizacao?: number,
    public imei?: string,
    public email?: string,
    public codigoBanca?: number,
    public revendedor?: IRevendedor
  ) {
    this.situacao = this.situacao || false;
  }
}
