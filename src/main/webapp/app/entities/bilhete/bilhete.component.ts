import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBilhete } from 'app/shared/model/bilhete.model';
import { BilheteService } from './bilhete.service';
import { BilheteDeleteDialogComponent } from './bilhete-delete-dialog.component';

@Component({
  selector: 'jhi-bilhete',
  templateUrl: './bilhete.component.html'
})
export class BilheteComponent implements OnInit, OnDestroy {
  bilhetes?: IBilhete[];
  eventSubscriber?: Subscription;

  constructor(protected bilheteService: BilheteService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.bilheteService.query().subscribe((res: HttpResponse<IBilhete[]>) => (this.bilhetes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBilhetes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBilhete): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBilhetes(): void {
    this.eventSubscriber = this.eventManager.subscribe('bilheteListModification', () => this.loadAll());
  }

  delete(bilhete: IBilhete): void {
    const modalRef = this.modalService.open(BilheteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bilhete = bilhete;
  }
}
