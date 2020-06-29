import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILoteria, Loteria } from 'app/shared/model/loteria.model';
import { LoteriaService } from './loteria.service';
import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { DiasFuncionamentoService } from 'app/entities/dias-funcionamento/dias-funcionamento.service';

@Component({
  selector: 'jhi-loteria-update',
  templateUrl: './loteria-update.component.html'
})
export class LoteriaUpdateComponent implements OnInit {
  isSaving = false;
  diasfuncionamentos: IDiasFuncionamento[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    horaEncerramento: [],
    premiacaoInicio: [null, [Validators.required]],
    status: [],
    limitePremio: [null, [Validators.required]],
    data: [],
    codigo: [null, [Validators.required]],
    descricao: [],
    hora: [null, [Validators.required]],
    minuto: [null, [Validators.required]],
    disponivel: [],
    descricaoCompleta: [null, [Validators.required]],
    diasFuncionamentos: []
  });

  constructor(
    protected loteriaService: LoteriaService,
    protected diasFuncionamentoService: DiasFuncionamentoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ loteria }) => {
      if (!loteria.id) {
        const today = moment().startOf('day');
        loteria.data = today;
      }

      this.updateForm(loteria);

      this.diasFuncionamentoService
        .query()
        .subscribe((res: HttpResponse<IDiasFuncionamento[]>) => (this.diasfuncionamentos = res.body || []));
    });
  }

  updateForm(loteria: ILoteria): void {
    this.editForm.patchValue({
      id: loteria.id,
      nome: loteria.nome,
      horaEncerramento: loteria.horaEncerramento,
      premiacaoInicio: loteria.premiacaoInicio,
      status: loteria.status,
      limitePremio: loteria.limitePremio,
      data: loteria.data ? loteria.data.format(DATE_TIME_FORMAT) : null,
      codigo: loteria.codigo,
      descricao: loteria.descricao,
      hora: loteria.hora,
      minuto: loteria.minuto,
      disponivel: loteria.disponivel,
      descricaoCompleta: loteria.descricaoCompleta,
      diasFuncionamentos: loteria.diasFuncionamentos
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const loteria = this.createFromForm();
    if (loteria.id !== undefined) {
      this.subscribeToSaveResponse(this.loteriaService.update(loteria));
    } else {
      this.subscribeToSaveResponse(this.loteriaService.create(loteria));
    }
  }

  private createFromForm(): ILoteria {
    return {
      ...new Loteria(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      horaEncerramento: this.editForm.get(['horaEncerramento'])!.value,
      premiacaoInicio: this.editForm.get(['premiacaoInicio'])!.value,
      status: this.editForm.get(['status'])!.value,
      limitePremio: this.editForm.get(['limitePremio'])!.value,
      data: this.editForm.get(['data'])!.value ? moment(this.editForm.get(['data'])!.value, DATE_TIME_FORMAT) : undefined,
      codigo: this.editForm.get(['codigo'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      hora: this.editForm.get(['hora'])!.value,
      minuto: this.editForm.get(['minuto'])!.value,
      disponivel: this.editForm.get(['disponivel'])!.value,
      descricaoCompleta: this.editForm.get(['descricaoCompleta'])!.value,
      diasFuncionamentos: this.editForm.get(['diasFuncionamentos'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILoteria>>): void {
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

  trackById(index: number, item: IDiasFuncionamento): any {
    return item.id;
  }

  getSelected(selectedVals: IDiasFuncionamento[], option: IDiasFuncionamento): IDiasFuncionamento {
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
