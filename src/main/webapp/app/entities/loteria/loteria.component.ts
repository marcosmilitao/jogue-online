import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILoteria } from 'app/shared/model/loteria.model';
import { LoteriaService } from './loteria.service';
import { LoteriaDeleteDialogComponent } from './loteria-delete-dialog.component';

@Component({
  selector: 'jhi-loteria',
  templateUrl: './loteria.component.html'
})
export class LoteriaComponent implements OnInit, OnDestroy {
  loterias?: ILoteria[];
  eventSubscriber?: Subscription;

  constructor(protected loteriaService: LoteriaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.loteriaService.query().subscribe((res: HttpResponse<ILoteria[]>) => (this.loterias = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLoterias();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILoteria): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLoterias(): void {
    this.eventSubscriber = this.eventManager.subscribe('loteriaListModification', () => this.loadAll());
  }

  delete(loteria: ILoteria): void {
    const modalRef = this.modalService.open(LoteriaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.loteria = loteria;
  }
}
