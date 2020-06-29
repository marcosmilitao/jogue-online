import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILoteria } from 'app/shared/model/loteria.model';

@Component({
  selector: 'jhi-loteria-detail',
  templateUrl: './loteria-detail.component.html'
})
export class LoteriaDetailComponent implements OnInit {
  loteria: ILoteria | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ loteria }) => (this.loteria = loteria));
  }

  previousState(): void {
    window.history.back();
  }
}
