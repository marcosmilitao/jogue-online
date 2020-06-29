import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from './banca.service';
import { BancaDeleteDialogComponent } from './banca-delete-dialog.component';

@Component({
  selector: 'jhi-banca',
  templateUrl: './banca.component.html'
})
export class BancaComponent implements OnInit, OnDestroy {
  bancas?: IBanca[];
  eventSubscriber?: Subscription;

  constructor(protected bancaService: BancaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.bancaService.query().subscribe((res: HttpResponse<IBanca[]>) => (this.bancas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBancas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBanca): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBancas(): void {
    this.eventSubscriber = this.eventManager.subscribe('bancaListModification', () => this.loadAll());
  }

  delete(banca: IBanca): void {
    const modalRef = this.modalService.open(BancaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.banca = banca;
  }
}
