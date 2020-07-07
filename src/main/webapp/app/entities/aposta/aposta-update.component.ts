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
import { IBilhete } from 'app/shared/model/bilhete.model';
import { BilheteService } from 'app/entities/bilhete/bilhete.service';

@Component({
  selector: 'jhi-aposta-update',
  templateUrl: './aposta-update.component.html'
})
export class ApostaUpdateComponent implements OnInit {
  isSaving = false;
  bilhetes: IBilhete[] = [];

  editForm = this.fb.group({
    id: [],
    numeroBilhete: [null, [Validators.required]],
    dataAposta: [null, [Validators.required]],
    loteriaCodigo: [null, [Validators.required]],
    modalide: [],
    codigoModalidade: [null, [Validators.required]],
    premio: [],
    codigoPremio: [null, [Validators.required]],
    valorJogo: [],
    codigoBanca: [],
    numeroAposta: [null, [Validators.required]],
    bilhete: []
  });

  constructor(
    protected apostaService: ApostaService,
    protected bilheteService: BilheteService,
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

      this.bilheteService.query().subscribe((res: HttpResponse<IBilhete[]>) => (this.bilhetes = res.body || []));
    });
  }

  updateForm(aposta: IAposta): void {
    this.editForm.patchValue({
      id: aposta.id,
      numeroBilhete: aposta.numeroBilhete,
      dataAposta: aposta.dataAposta ? aposta.dataAposta.format(DATE_TIME_FORMAT) : null,
      loteriaCodigo: aposta.loteriaCodigo,
      modalide: aposta.modalide,
      codigoModalidade: aposta.codigoModalidade,
      premio: aposta.premio,
      codigoPremio: aposta.codigoPremio,
      valorJogo: aposta.valorJogo,
      codigoBanca: aposta.codigoBanca,
      numeroAposta: aposta.numeroAposta,
      bilhete: aposta.bilhete
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
      numeroBilhete: this.editForm.get(['numeroBilhete'])!.value,
      dataAposta: this.editForm.get(['dataAposta'])!.value ? moment(this.editForm.get(['dataAposta'])!.value, DATE_TIME_FORMAT) : undefined,
      loteriaCodigo: this.editForm.get(['loteriaCodigo'])!.value,
      modalide: this.editForm.get(['modalide'])!.value,
      codigoModalidade: this.editForm.get(['codigoModalidade'])!.value,
      premio: this.editForm.get(['premio'])!.value,
      codigoPremio: this.editForm.get(['codigoPremio'])!.value,
      valorJogo: this.editForm.get(['valorJogo'])!.value,
      codigoBanca: this.editForm.get(['codigoBanca'])!.value,
      numeroAposta: this.editForm.get(['numeroAposta'])!.value,
      bilhete: this.editForm.get(['bilhete'])!.value
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

  trackById(index: number, item: IBilhete): any {
    return item.id;
  }
}
