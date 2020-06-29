import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPromotor, Promotor } from 'app/shared/model/promotor.model';
import { PromotorService } from './promotor.service';
import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from 'app/entities/banca/banca.service';

@Component({
  selector: 'jhi-promotor-update',
  templateUrl: './promotor-update.component.html'
})
export class PromotorUpdateComponent implements OnInit {
  isSaving = false;
  bancas: IBanca[] = [];

  editForm = this.fb.group({
    id: [],
    codigo: [null, [Validators.required]],
    nome: [null, [Validators.required]],
    cidade: [],
    estado: [],
    telefone: [null, [Validators.required]],
    comissao: [],
    data: [],
    banca: []
  });

  constructor(
    protected promotorService: PromotorService,
    protected bancaService: BancaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promotor }) => {
      if (!promotor.id) {
        const today = moment().startOf('day');
        promotor.data = today;
      }

      this.updateForm(promotor);

      this.bancaService.query().subscribe((res: HttpResponse<IBanca[]>) => (this.bancas = res.body || []));
    });
  }

  updateForm(promotor: IPromotor): void {
    this.editForm.patchValue({
      id: promotor.id,
      codigo: promotor.codigo,
      nome: promotor.nome,
      cidade: promotor.cidade,
      estado: promotor.estado,
      telefone: promotor.telefone,
      comissao: promotor.comissao,
      data: promotor.data ? promotor.data.format(DATE_TIME_FORMAT) : null,
      banca: promotor.banca
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promotor = this.createFromForm();
    if (promotor.id !== undefined) {
      this.subscribeToSaveResponse(this.promotorService.update(promotor));
    } else {
      this.subscribeToSaveResponse(this.promotorService.create(promotor));
    }
  }

  private createFromForm(): IPromotor {
    return {
      ...new Promotor(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      telefone: this.editForm.get(['telefone'])!.value,
      comissao: this.editForm.get(['comissao'])!.value,
      data: this.editForm.get(['data'])!.value ? moment(this.editForm.get(['data'])!.value, DATE_TIME_FORMAT) : undefined,
      banca: this.editForm.get(['banca'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromotor>>): void {
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
