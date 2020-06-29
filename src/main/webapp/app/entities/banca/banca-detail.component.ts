import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBanca } from 'app/shared/model/banca.model';

@Component({
  selector: 'jhi-banca-detail',
  templateUrl: './banca-detail.component.html'
})
export class BancaDetailComponent implements OnInit {
  banca: IBanca | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banca }) => (this.banca = banca));
  }

  previousState(): void {
    window.history.back();
  }
}
