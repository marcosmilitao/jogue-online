import { IRevendedor } from 'app/shared/model/revendedor.model';

export interface ILimiteVenda {
  id?: number;
  codigoRevendedor?: number;
  vendaDia?: number;
  debitoAtual?: number;
  limite?: number;
  revendedor?: IRevendedor;
}

export class LimiteVenda implements ILimiteVenda {
  constructor(
    public id?: number,
    public codigoRevendedor?: number,
    public vendaDia?: number,
    public debitoAtual?: number,
    public limite?: number,
    public revendedor?: IRevendedor
  ) {}
}
