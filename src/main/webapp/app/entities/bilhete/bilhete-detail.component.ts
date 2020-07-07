import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBilhete } from 'app/shared/model/bilhete.model';

@Component({
  selector: 'jhi-bilhete-detail',
  templateUrl: './bilhete-detail.component.html'
})
export class BilheteDetailComponent implements OnInit {
  bilhete: IBilhete | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bilhete }) => (this.bilhete = bilhete));
  }

  previousState(): void {
    window.history.back();
  }
}
