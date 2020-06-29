import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPremio } from 'app/shared/model/premio.model';
import { PremioService } from './premio.service';
import { PremioDeleteDialogComponent } from './premio-delete-dialog.component';

@Component({
  selector: 'jhi-premio',
  templateUrl: './premio.component.html'
})
export class PremioComponent implements OnInit, OnDestroy {
  premios?: IPremio[];
  eventSubscriber?: Subscription;

  constructor(protected premioService: PremioService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.premioService.query().subscribe((res: HttpResponse<IPremio[]>) => (this.premios = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPremios();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPremio): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPremios(): void {
    this.eventSubscriber = this.eventManager.subscribe('premioListModification', () => this.loadAll());
  }

  delete(premio: IPremio): void {
    const modalRef = this.modalService.open(PremioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.premio = premio;
  }
}
