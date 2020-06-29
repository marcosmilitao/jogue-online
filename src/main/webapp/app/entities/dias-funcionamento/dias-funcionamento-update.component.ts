import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDiasFuncionamento, DiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { DiasFuncionamentoService } from './dias-funcionamento.service';

@Component({
  selector: 'jhi-dias-funcionamento-update',
  templateUrl: './dias-funcionamento-update.component.html'
})
export class DiasFuncionamentoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dia: [null, [Validators.required]]
  });

  constructor(
    protected diasFuncionamentoService: DiasFuncionamentoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ diasFuncionamento }) => {
      this.updateForm(diasFuncionamento);
    });
  }

  updateForm(diasFuncionamento: IDiasFuncionamento): void {
    this.editForm.patchValue({
      id: diasFuncionamento.id,
      dia: diasFuncionamento.dia
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const diasFuncionamento = this.createFromForm();
    if (diasFuncionamento.id !== undefined) {
      this.subscribeToSaveResponse(this.diasFuncionamentoService.update(diasFuncionamento));
    } else {
      this.subscribeToSaveResponse(this.diasFuncionamentoService.create(diasFuncionamento));
    }
  }

  private createFromForm(): IDiasFuncionamento {
    return {
      ...new DiasFuncionamento(),
      id: this.editForm.get(['id'])!.value,
      dia: this.editForm.get(['dia'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDiasFuncionamento>>): void {
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
}
