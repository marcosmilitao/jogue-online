import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBanca, Banca } from 'app/shared/model/banca.model';
import { BancaService } from './banca.service';
import { IModalidade } from 'app/shared/model/modalidade.model';
import { ModalidadeService } from 'app/entities/modalidade/modalidade.service';
import { ILoteria } from 'app/shared/model/loteria.model';
import { LoteriaService } from 'app/entities/loteria/loteria.service';
import { ICustomUser } from 'app/shared/model/custom-user.model';
import { CustomUserService } from 'app/entities/custom-user/custom-user.service';

type SelectableEntity = IModalidade | ILoteria | ICustomUser;

@Component({
  selector: 'jhi-banca-update',
  templateUrl: './banca-update.component.html'
})
export class BancaUpdateComponent implements OnInit {
  isSaving = false;
  modalidades: IModalidade[] = [];
  loterias: ILoteria[] = [];
  customusers: ICustomUser[] = [];

  editForm = this.fb.group({
    id: [],
    codigo: [null, [Validators.required]],
    nome: [null, [Validators.required]],
    cidade: [],
    telefone: [],
    estado: [],
    limiteDescarga: [],
    limitePremiacao: [],
    limiteBaixaAutomatica: [],
    limiteHorarioEncerramento: [],
    mensagemPule1: [],
    mensagemPule2: [],
    mensagemPule3: [],
    data: [],
    modalidades: [],
    loterias: [],
    customUsers: []
  });

  constructor(
    protected bancaService: BancaService,
    protected modalidadeService: ModalidadeService,
    protected loteriaService: LoteriaService,
    protected customUserService: CustomUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banca }) => {
      if (!banca.id) {
        const today = moment().startOf('day');
        banca.data = today;
      }

      this.updateForm(banca);

      this.modalidadeService.query().subscribe((res: HttpResponse<IModalidade[]>) => (this.modalidades = res.body || []));

      this.loteriaService.query().subscribe((res: HttpResponse<ILoteria[]>) => (this.loterias = res.body || []));

      this.customUserService.query().subscribe((res: HttpResponse<ICustomUser[]>) => (this.customusers = res.body || []));
    });
  }

  updateForm(banca: IBanca): void {
    this.editForm.patchValue({
      id: banca.id,
      codigo: banca.codigo,
      nome: banca.nome,
      cidade: banca.cidade,
      telefone: banca.telefone,
      estado: banca.estado,
      limiteDescarga: banca.limiteDescarga,
      limitePremiacao: banca.limitePremiacao,
      limiteBaixaAutomatica: banca.limiteBaixaAutomatica,
      limiteHorarioEncerramento: banca.limiteHorarioEncerramento,
      mensagemPule1: banca.mensagemPule1,
      mensagemPule2: banca.mensagemPule2,
      mensagemPule3: banca.mensagemPule3,
      data: banca.data ? banca.data.format(DATE_TIME_FORMAT) : null,
      modalidades: banca.modalidades,
      loterias: banca.loterias,
      customUsers: banca.customUsers
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const banca = this.createFromForm();
    if (banca.id !== undefined) {
      this.subscribeToSaveResponse(this.bancaService.update(banca));
    } else {
      this.subscribeToSaveResponse(this.bancaService.create(banca));
    }
  }

  private createFromForm(): IBanca {
    return {
      ...new Banca(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      telefone: this.editForm.get(['telefone'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      limiteDescarga: this.editForm.get(['limiteDescarga'])!.value,
      limitePremiacao: this.editForm.get(['limitePremiacao'])!.value,
      limiteBaixaAutomatica: this.editForm.get(['limiteBaixaAutomatica'])!.value,
      limiteHorarioEncerramento: this.editForm.get(['limiteHorarioEncerramento'])!.value,
      mensagemPule1: this.editForm.get(['mensagemPule1'])!.value,
      mensagemPule2: this.editForm.get(['mensagemPule2'])!.value,
      mensagemPule3: this.editForm.get(['mensagemPule3'])!.value,
      data: this.editForm.get(['data'])!.value ? moment(this.editForm.get(['data'])!.value, DATE_TIME_FORMAT) : undefined,
      modalidades: this.editForm.get(['modalidades'])!.value,
      loterias: this.editForm.get(['loterias'])!.value,
      customUsers: this.editForm.get(['customUsers'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanca>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}