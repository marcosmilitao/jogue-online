import { IModalidade } from 'app/shared/model/modalidade.model';

export interface IPremio {
  id?: number;
  codigo?: number;
  nome?: string;
  modalidades?: IModalidade[];
}

export class Premio implements IPremio {
  constructor(public id?: number, public codigo?: number, public nome?: string, public modalidades?: IModalidade[]) {}
}
