import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAposta } from 'app/shared/model/aposta.model';

@Component({
  selector: 'jhi-aposta-detail',
  templateUrl: './aposta-detail.component.html'
})
export class ApostaDetailComponent implements OnInit {
  aposta: IAposta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aposta }) => (this.aposta = aposta));
  }

  previousState(): void {
    window.history.back();
  }
}
