import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPromotor } from 'app/shared/model/promotor.model';
import { PromotorService } from './promotor.service';
import { PromotorDeleteDialogComponent } from './promotor-delete-dialog.component';

@Component({
  selector: 'jhi-promotor',
  templateUrl: './promotor.component.html'
})
export class PromotorComponent implements OnInit, OnDestroy {
  promotors?: IPromotor[];
  eventSubscriber?: Subscription;

  constructor(protected promotorService: PromotorService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.promotorService.query().subscribe((res: HttpResponse<IPromotor[]>) => (this.promotors = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPromotors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPromotor): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPromotors(): void {
    this.eventSubscriber = this.eventManager.subscribe('promotorListModification', () => this.loadAll());
  }

  delete(promotor: IPromotor): void {
    const modalRef = this.modalService.open(PromotorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.promotor = promotor;
  }
}
