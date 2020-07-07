import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRevendedor, Revendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from './revendedor.service';
import { IPromotor } from 'app/shared/model/promotor.model';
import { PromotorService } from 'app/entities/promotor/promotor.service';

@Component({
  selector: 'jhi-revendedor-update',
  templateUrl: './revendedor-update.component.html'
})
export class RevendedorUpdateComponent implements OnInit {
  isSaving = false;
  promotors: IPromotor[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    cidade: [],
    estado: [],
    telefone: [null, [Validators.required]],
    tipo: [],
    tipoColetor: [],
    serialColetor: [],
    nomeComercial: [],
    situacao: [],
    saldo: [],
    senha: [],
    data: [],
    comissao: [],
    promotor: []
  });

  constructor(
    protected revendedorService: RevendedorService,
    protected promotorService: PromotorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ revendedor }) => {
      if (!revendedor.id) {
        const today = moment().startOf('day');
        revendedor.data = today;
      }

      this.updateForm(revendedor);

      this.promotorService.query().subscribe((res: HttpResponse<IPromotor[]>) => (this.promotors = res.body || []));
    });
  }

  updateForm(revendedor: IRevendedor): void {
    this.editForm.patchValue({
      id: revendedor.id,
      nome: revendedor.nome,
      cidade: revendedor.cidade,
      estado: revendedor.estado,
      telefone: revendedor.telefone,
      tipo: revendedor.tipo,
      tipoColetor: revendedor.tipoColetor,
      serialColetor: revendedor.serialColetor,
      nomeComercial: revendedor.nomeComercial,
      situacao: revendedor.situacao,
      saldo: revendedor.saldo,
      senha: revendedor.senha,
      data: revendedor.data ? revendedor.data.format(DATE_TIME_FORMAT) : null,
      comissao: revendedor.comissao,
      promotor: revendedor.promotor
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const revendedor = this.createFromForm();
    if (revendedor.id !== undefined) {
      this.subscribeToSaveResponse(this.revendedorService.update(revendedor));
    } else {
      this.subscribeToSaveResponse(this.revendedorService.create(revendedor));
    }
  }

  private createFromForm(): IRevendedor {
    return {
      ...new Revendedor(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      telefone: this.editForm.get(['telefone'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      tipoColetor: this.editForm.get(['tipoColetor'])!.value,
      serialColetor: this.editForm.get(['serialColetor'])!.value,
      nomeComercial: this.editForm.get(['nomeComercial'])!.value,
      situacao: this.editForm.get(['situacao'])!.value,
      saldo: this.editForm.get(['saldo'])!.value,
      senha: this.editForm.get(['senha'])!.value,
      data: this.editForm.get(['data'])!.value ? moment(this.editForm.get(['data'])!.value, DATE_TIME_FORMAT) : undefined,
      comissao: this.editForm.get(['comissao'])!.value,
      promotor: this.editForm.get(['promotor'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRevendedor>>): void {
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

  trackById(index: number, item: IPromotor): any {
    return item.id;
  }
}
