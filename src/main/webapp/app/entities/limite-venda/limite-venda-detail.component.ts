import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILimiteVenda } from 'app/shared/model/limite-venda.model';

@Component({
  selector: 'jhi-limite-venda-detail',
  templateUrl: './limite-venda-detail.component.html'
})
export class LimiteVendaDetailComponent implements OnInit {
  limiteVenda: ILimiteVenda | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ limiteVenda }) => (this.limiteVenda = limiteVenda));
  }

  previousState(): void {
    window.history.back();
  }
}
