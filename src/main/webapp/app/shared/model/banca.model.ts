import { Moment } from 'moment';
import { ISaldo } from 'app/shared/model/saldo.model';
import { IPromotor } from 'app/shared/model/promotor.model';
import { IBilhete } from 'app/shared/model/bilhete.model';
import { IModalidade } from 'app/shared/model/modalidade.model';
import { ILoteria } from 'app/shared/model/loteria.model';
import { ICustomUser } from 'app/shared/model/custom-user.model';

export interface IBanca {
  id?: number;
  nome?: string;
  cidade?: string;
  telefone?: string;
  estado?: string;
  limiteDescarga?: number;
  limitePremiacao?: number;
  limiteBaixaAutomatica?: number;
  limiteHorarioEncerramento?: number;
  mensagemPule1?: string;
  mensagemPule2?: string;
  mensagemPule3?: string;
  data?: Moment;
  bonus?: number;
  saldo?: ISaldo;
  promotors?: IPromotor[];
  bilhetes?: IBilhete[];
  modalidades?: IModalidade[];
  loterias?: ILoteria[];
  customUsers?: ICustomUser[];
}

export class Banca implements IBanca {
  constructor(
    public id?: number,
    public nome?: string,
    public cidade?: string,
    public telefone?: string,
    public estado?: string,
    public limiteDescarga?: number,
    public limitePremiacao?: number,
    public limiteBaixaAutomatica?: number,
    public limiteHorarioEncerramento?: number,
    public mensagemPule1?: string,
    public mensagemPule2?: string,
    public mensagemPule3?: string,
    public data?: Moment,
    public bonus?: number,
    public saldo?: ISaldo,
    public promotors?: IPromotor[],
    public bilhetes?: IBilhete[],
    public modalidades?: IModalidade[],
    public loterias?: ILoteria[],
    public customUsers?: ICustomUser[]
  ) {}
}
