import { Moment } from 'moment';
import { IPromotor } from 'app/shared/model/promotor.model';
import { ITerminal } from 'app/shared/model/terminal.model';
import { IAposta } from 'app/shared/model/aposta.model';
import { IModalidade } from 'app/shared/model/modalidade.model';
import { ILoteria } from 'app/shared/model/loteria.model';
import { ICustomUser } from 'app/shared/model/custom-user.model';

export interface IBanca {
  id?: number;
  codigo?: number;
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
  promotors?: IPromotor[];
  cadastroTerminals?: ITerminal[];
  apostas?: IAposta[];
  modalidades?: IModalidade[];
  loterias?: ILoteria[];
  customUsers?: ICustomUser[];
}

export class Banca implements IBanca {
  constructor(
    public id?: number,
    public codigo?: number,
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
    public promotors?: IPromotor[],
    public cadastroTerminals?: ITerminal[],
    public apostas?: IAposta[],
    public modalidades?: IModalidade[],
    public loterias?: ILoteria[],
    public customUsers?: ICustomUser[]
  ) {}
}
