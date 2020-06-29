import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAposta, Aposta } from 'app/shared/model/aposta.model';
import { ApostaService } from './aposta.service';
import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from 'app/entities/banca/banca.service';

@Component({
  selector: 'jhi-aposta-update',
  templateUrl: './aposta-update.component.html'
})
export class ApostaUpdateComponent implements OnInit {
  isSaving = false;
  bancas: IBanca[] = [];

  editForm = this.fb.group({
    id: [],
    codigoJogo: [null, []],
    dataAposta: [],
    loteriaNome: [],
    loteriaCodigo: [],
    modalide: [],
    codigoModalidade: [],
    premio: [],
    codigoPremio: [],
    valorJogo: [],
    codigoBanca: [],
    banca: []
  });

  constructor(
    protected apostaService: ApostaService,
    protected bancaService: BancaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aposta }) => {
      if (!aposta.id) {
        const today = moment().startOf('day');
        aposta.dataAposta = today;
      }

      this.updateForm(aposta);

      this.bancaService.query().subscribe((res: HttpResponse<IBanca[]>) => (this.bancas = res.body || []));
    });
  }

  updateForm(aposta: IAposta): void {
    this.editForm.patchValue({
      id: aposta.id,
      codigoJogo: aposta.codigoJogo,
      dataAposta: aposta.dataAposta ? aposta.dataAposta.format(DATE_TIME_FORMAT) : null,
      loteriaNome: aposta.loteriaNome,
      loteriaCodigo: aposta.loteriaCodigo,
      modalide: aposta.modalide,
      codigoModalidade: aposta.codigoModalidade,
      premio: aposta.premio,
      codigoPremio: aposta.codigoPremio,
      valorJogo: aposta.valorJogo,
      codigoBanca: aposta.codigoBanca,
      banca: aposta.banca
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const aposta = this.createFromForm();
    if (aposta.id !== undefined) {
      this.subscribeToSaveResponse(this.apostaService.update(aposta));
    } else {
      this.subscribeToSaveResponse(this.apostaService.create(aposta));
    }
  }

  private createFromForm(): IAposta {
    return {
      ...new Aposta(),
      id: this.editForm.get(['id'])!.value,
      codigoJogo: this.editForm.get(['codigoJogo'])!.value,
      dataAposta: this.editForm.get(['dataAposta'])!.value ? moment(this.editForm.get(['dataAposta'])!.value, DATE_TIME_FORMAT) : undefined,
      loteriaNome: this.editForm.get(['loteriaNome'])!.value,
      loteriaCodigo: this.editForm.get(['loteriaCodigo'])!.value,
      modalide: this.editForm.get(['modalide'])!.value,
      codigoModalidade: this.editForm.get(['codigoModalidade'])!.value,
      premio: this.editForm.get(['premio'])!.value,
      codigoPremio: this.editForm.get(['codigoPremio'])!.value,
      valorJogo: this.editForm.get(['valorJogo'])!.value,
      codigoBanca: this.editForm.get(['codigoBanca'])!.value,
      banca: this.editForm.get(['banca'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAposta>>): void {
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

  trackById(index: number, item: IBanca): any {
    return item.id;
  }
}
