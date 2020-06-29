import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModalidade } from 'app/shared/model/modalidade.model';

@Component({
  selector: 'jhi-modalidade-detail',
  templateUrl: './modalidade-detail.component.html'
})
export class ModalidadeDetailComponent implements OnInit {
  modalidade: IModalidade | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modalidade }) => (this.modalidade = modalidade));
  }

  previousState(): void {
    window.history.back();
  }
}
