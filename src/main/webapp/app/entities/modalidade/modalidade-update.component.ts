import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModalidade, Modalidade } from 'app/shared/model/modalidade.model';
import { ModalidadeService } from './modalidade.service';

@Component({
  selector: 'jhi-modalidade-update',
  templateUrl: './modalidade-update.component.html'
})
export class ModalidadeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codigoModalidade: [null, [Validators.required]],
    nome: [null, [Validators.required]],
    menorPalpite: [null, [Validators.required]],
    maiorPalpite: [null, [Validators.required]],
    qtdePalpites: [],
    qtdeMinimaPalpites: [null, [Validators.required]],
    qtdeCaracteres: [null, [Validators.required]],
    qtdeMinimaCaracteres: [null, [Validators.required]],
    menorValor: [null, [Validators.required]],
    maiorValor: [null, [Validators.required]],
    maiorValorExcessao: [],
    repeticao: [],
    mascara: [],
    palpiteMultiplo: [],
    palpiteMultiploTerminal: [],
    ordenar: [],
    permitePalpiteAleatorio: []
  });

  constructor(protected modalidadeService: ModalidadeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modalidade }) => {
      this.updateForm(modalidade);
    });
  }

  updateForm(modalidade: IModalidade): void {
    this.editForm.patchValue({
      id: modalidade.id,
      codigoModalidade: modalidade.codigoModalidade,
      nome: modalidade.nome,
      menorPalpite: modalidade.menorPalpite,
      maiorPalpite: modalidade.maiorPalpite,
      qtdePalpites: modalidade.qtdePalpites,
      qtdeMinimaPalpites: modalidade.qtdeMinimaPalpites,
      qtdeCaracteres: modalidade.qtdeCaracteres,
      qtdeMinimaCaracteres: modalidade.qtdeMinimaCaracteres,
      menorValor: modalidade.menorValor,
      maiorValor: modalidade.maiorValor,
      maiorValorExcessao: modalidade.maiorValorExcessao,
      repeticao: modalidade.repeticao,
      mascara: modalidade.mascara,
      palpiteMultiplo: modalidade.palpiteMultiplo,
      palpiteMultiploTerminal: modalidade.palpiteMultiploTerminal,
      ordenar: modalidade.ordenar,
      permitePalpiteAleatorio: modalidade.permitePalpiteAleatorio
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modalidade = this.createFromForm();
    if (modalidade.id !== undefined) {
      this.subscribeToSaveResponse(this.modalidadeService.update(modalidade));
    } else {
      this.subscribeToSaveResponse(this.modalidadeService.create(modalidade));
    }
  }

  private createFromForm(): IModalidade {
    return {
      ...new Modalidade(),
      id: this.editForm.get(['id'])!.value,
      codigoModalidade: this.editForm.get(['codigoModalidade'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      menorPalpite: this.editForm.get(['menorPalpite'])!.value,
      maiorPalpite: this.editForm.get(['maiorPalpite'])!.value,
      qtdePalpites: this.editForm.get(['qtdePalpites'])!.value,
      qtdeMinimaPalpites: this.editForm.get(['qtdeMinimaPalpites'])!.value,
      qtdeCaracteres: this.editForm.get(['qtdeCaracteres'])!.value,
      qtdeMinimaCaracteres: this.editForm.get(['qtdeMinimaCaracteres'])!.value,
      menorValor: this.editForm.get(['menorValor'])!.value,
      maiorValor: this.editForm.get(['maiorValor'])!.value,
      maiorValorExcessao: this.editForm.get(['maiorValorExcessao'])!.value,
      repeticao: this.editForm.get(['repeticao'])!.value,
      mascara: this.editForm.get(['mascara'])!.value,
      palpiteMultiplo: this.editForm.get(['palpiteMultiplo'])!.value,
      palpiteMultiploTerminal: this.editForm.get(['palpiteMultiploTerminal'])!.value,
      ordenar: this.editForm.get(['ordenar'])!.value,
      permitePalpiteAleatorio: this.editForm.get(['permitePalpiteAleatorio'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModalidade>>): void {
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
