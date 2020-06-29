import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRevendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from './revendedor.service';
import { RevendedorDeleteDialogComponent } from './revendedor-delete-dialog.component';

@Component({
  selector: 'jhi-revendedor',
  templateUrl: './revendedor.component.html'
})
export class RevendedorComponent implements OnInit, OnDestroy {
  revendedors?: IRevendedor[];
  eventSubscriber?: Subscription;

  constructor(protected revendedorService: RevendedorService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.revendedorService.query().subscribe((res: HttpResponse<IRevendedor[]>) => (this.revendedors = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRevendedors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRevendedor): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRevendedors(): void {
    this.eventSubscriber = this.eventManager.subscribe('revendedorListModification', () => this.loadAll());
  }

  delete(revendedor: IRevendedor): void {
    const modalRef = this.modalService.open(RevendedorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.revendedor = revendedor;
  }
}
