import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPremio, Premio } from 'app/shared/model/premio.model';
import { PremioService } from './premio.service';

@Component({
  selector: 'jhi-premio-update',
  templateUrl: './premio-update.component.html'
})
export class PremioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codigo: [],
    nome: []
  });

  constructor(protected premioService: PremioService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ premio }) => {
      this.updateForm(premio);
    });
  }

  updateForm(premio: IPremio): void {
    this.editForm.patchValue({
      id: premio.id,
      codigo: premio.codigo,
      nome: premio.nome
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const premio = this.createFromForm();
    if (premio.id !== undefined) {
      this.subscribeToSaveResponse(this.premioService.update(premio));
    } else {
      this.subscribeToSaveResponse(this.premioService.create(premio));
    }
  }

  private createFromForm(): IPremio {
    return {
      ...new Premio(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      nome: this.editForm.get(['nome'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPremio>>): void {
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
