import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILimiteVenda } from 'app/shared/model/limite-venda.model';
import { LimiteVendaService } from './limite-venda.service';
import { LimiteVendaDeleteDialogComponent } from './limite-venda-delete-dialog.component';

@Component({
  selector: 'jhi-limite-venda',
  templateUrl: './limite-venda.component.html'
})
export class LimiteVendaComponent implements OnInit, OnDestroy {
  limiteVendas?: ILimiteVenda[];
  eventSubscriber?: Subscription;

  constructor(
    protected limiteVendaService: LimiteVendaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.limiteVendaService.query().subscribe((res: HttpResponse<ILimiteVenda[]>) => (this.limiteVendas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLimiteVendas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILimiteVenda): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLimiteVendas(): void {
    this.eventSubscriber = this.eventManager.subscribe('limiteVendaListModification', () => this.loadAll());
  }

  delete(limiteVenda: ILimiteVenda): void {
    const modalRef = this.modalService.open(LimiteVendaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.limiteVenda = limiteVenda;
  }
}
