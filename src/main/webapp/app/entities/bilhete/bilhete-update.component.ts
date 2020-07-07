import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBilhete, Bilhete } from 'app/shared/model/bilhete.model';
import { BilheteService } from './bilhete.service';
import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from 'app/entities/banca/banca.service';

@Component({
  selector: 'jhi-bilhete-update',
  templateUrl: './bilhete-update.component.html'
})
export class BilheteUpdateComponent implements OnInit {
  isSaving = false;
  bancas: IBanca[] = [];

  editForm = this.fb.group({
    id: [],
    numeroBilhete: [null, [Validators.required]],
    codigoBanca: [null, [Validators.required]],
    codigoLoteria: [null, [Validators.required]],
    loteriaNome: [],
    valorTotalAposta: [null, [Validators.required]],
    bonusBanca: [],
    bonusIndividual: [],
    comicao: [],
    valorBilhete: [null, [Validators.required]],
    dataHoraAposta: [null, [Validators.required]],
    qrcode: [],
    codigoTerminal: [null, [Validators.required]],
    banca: []
  });

  constructor(
    protected bilheteService: BilheteService,
    protected bancaService: BancaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bilhete }) => {
      if (!bilhete.id) {
        const today = moment().startOf('day');
        bilhete.dataHoraAposta = today;
      }

      this.updateForm(bilhete);

      this.bancaService.query().subscribe((res: HttpResponse<IBanca[]>) => (this.bancas = res.body || []));
    });
  }

  updateForm(bilhete: IBilhete): void {
    this.editForm.patchValue({
      id: bilhete.id,
      numeroBilhete: bilhete.numeroBilhete,
      codigoBanca: bilhete.codigoBanca,
      codigoLoteria: bilhete.codigoLoteria,
      loteriaNome: bilhete.loteriaNome,
      valorTotalAposta: bilhete.valorTotalAposta,
      bonusBanca: bilhete.bonusBanca,
      bonusIndividual: bilhete.bonusIndividual,
      comicao: bilhete.comicao,
      valorBilhete: bilhete.valorBilhete,
      dataHoraAposta: bilhete.dataHoraAposta ? bilhete.dataHoraAposta.format(DATE_TIME_FORMAT) : null,
      qrcode: bilhete.qrcode,
      codigoTerminal: bilhete.codigoTerminal,
      banca: bilhete.banca
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bilhete = this.createFromForm();
    if (bilhete.id !== undefined) {
      this.subscribeToSaveResponse(this.bilheteService.update(bilhete));
    } else {
      this.subscribeToSaveResponse(this.bilheteService.create(bilhete));
    }
  }

  private createFromForm(): IBilhete {
    return {
      ...new Bilhete(),
      id: this.editForm.get(['id'])!.value,
      numeroBilhete: this.editForm.get(['numeroBilhete'])!.value,
      codigoBanca: this.editForm.get(['codigoBanca'])!.value,
      codigoLoteria: this.editForm.get(['codigoLoteria'])!.value,
      loteriaNome: this.editForm.get(['loteriaNome'])!.value,
      valorTotalAposta: this.editForm.get(['valorTotalAposta'])!.value,
      bonusBanca: this.editForm.get(['bonusBanca'])!.value,
      bonusIndividual: this.editForm.get(['bonusIndividual'])!.value,
      comicao: this.editForm.get(['comicao'])!.value,
      valorBilhete: this.editForm.get(['valorBilhete'])!.value,
      dataHoraAposta: this.editForm.get(['dataHoraAposta'])!.value
        ? moment(this.editForm.get(['dataHoraAposta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      qrcode: this.editForm.get(['qrcode'])!.value,
      codigoTerminal: this.editForm.get(['codigoTerminal'])!.value,
      banca: this.editForm.get(['banca'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBilhete>>): void {
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
