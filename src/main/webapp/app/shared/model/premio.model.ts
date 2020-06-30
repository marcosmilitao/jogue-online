import { ILoteria } from 'app/shared/model/loteria.model';

export interface IPremio {
  id?: number;
  codigo?: number;
  nome?: string;
  loterias?: ILoteria[];
}

export class Premio implements IPremio {
  constructor(public id?: number, public codigo?: number, public nome?: string, public loterias?: ILoteria[]) {}
}
