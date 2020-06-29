import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';

@Component({
  selector: 'jhi-dias-funcionamento-detail',
  templateUrl: './dias-funcionamento-detail.component.html'
})
export class DiasFuncionamentoDetailComponent implements OnInit {
  diasFuncionamento: IDiasFuncionamento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ diasFuncionamento }) => (this.diasFuncionamento = diasFuncionamento));
  }

  previousState(): void {
    window.history.back();
  }
}
