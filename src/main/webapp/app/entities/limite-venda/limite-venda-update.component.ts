import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ILimiteVenda, LimiteVenda } from 'app/shared/model/limite-venda.model';
import { LimiteVendaService } from './limite-venda.service';
import { IRevendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from 'app/entities/revendedor/revendedor.service';

@Component({
  selector: 'jhi-limite-venda-update',
  templateUrl: './limite-venda-update.component.html'
})
export class LimiteVendaUpdateComponent implements OnInit {
  isSaving = false;
  revendedors: IRevendedor[] = [];

  editForm = this.fb.group({
    id: [],
    codigoRevendedor: [],
    vendaDia: [],
    debitoAtual: [],
    limite: [],
    revendedor: []
  });

  constructor(
    protected limiteVendaService: LimiteVendaService,
    protected revendedorService: RevendedorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ limiteVenda }) => {
      this.updateForm(limiteVenda);

      this.revendedorService
        .query({ filter: 'limitevenda-is-null' })
        .pipe(
          map((res: HttpResponse<IRevendedor[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRevendedor[]) => {
          if (!limiteVenda.revendedor || !limiteVenda.revendedor.id) {
            this.revendedors = resBody;
          } else {
            this.revendedorService
              .find(limiteVenda.revendedor.id)
              .pipe(
                map((subRes: HttpResponse<IRevendedor>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRevendedor[]) => (this.revendedors = concatRes));
          }
        });
    });
  }

  updateForm(limiteVenda: ILimiteVenda): void {
    this.editForm.patchValue({
      id: limiteVenda.id,
      codigoRevendedor: limiteVenda.codigoRevendedor,
      vendaDia: limiteVenda.vendaDia,
      debitoAtual: limiteVenda.debitoAtual,
      limite: limiteVenda.limite,
      revendedor: limiteVenda.revendedor
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const limiteVenda = this.createFromForm();
    if (limiteVenda.id !== undefined) {
      this.subscribeToSaveResponse(this.limiteVendaService.update(limiteVenda));
    } else {
      this.subscribeToSaveResponse(this.limiteVendaService.create(limiteVenda));
    }
  }

  private createFromForm(): ILimiteVenda {
    return {
      ...new LimiteVenda(),
      id: this.editForm.get(['id'])!.value,
      codigoRevendedor: this.editForm.get(['codigoRevendedor'])!.value,
      vendaDia: this.editForm.get(['vendaDia'])!.value,
      debitoAtual: this.editForm.get(['debitoAtual'])!.value,
      limite: this.editForm.get(['limite'])!.value,
      revendedor: this.editForm.get(['revendedor'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILimiteVenda>>): void {
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

  trackById(index: number, item: IRevendedor): any {
    return item.id;
  }
}
