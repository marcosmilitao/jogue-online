import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRevendedor } from 'app/shared/model/revendedor.model';

@Component({
  selector: 'jhi-revendedor-detail',
  templateUrl: './revendedor-detail.component.html'
})
export class RevendedorDetailComponent implements OnInit {
  revendedor: IRevendedor | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ revendedor }) => (this.revendedor = revendedor));
  }

  previousState(): void {
    window.history.back();
  }
}
