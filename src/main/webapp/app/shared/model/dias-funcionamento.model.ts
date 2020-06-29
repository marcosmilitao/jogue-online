import { ILoteria } from 'app/shared/model/loteria.model';

export interface IDiasFuncionamento {
  id?: number;
  dia?: string;
  loterias?: ILoteria[];
}

export class DiasFuncionamento implements IDiasFuncionamento {
  constructor(public id?: number, public dia?: string, public loterias?: ILoteria[]) {}
}
