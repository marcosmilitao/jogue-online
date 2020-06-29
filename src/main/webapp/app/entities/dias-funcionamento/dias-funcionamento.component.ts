import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { DiasFuncionamentoService } from './dias-funcionamento.service';
import { DiasFuncionamentoDeleteDialogComponent } from './dias-funcionamento-delete-dialog.component';

@Component({
  selector: 'jhi-dias-funcionamento',
  templateUrl: './dias-funcionamento.component.html'
})
export class DiasFuncionamentoComponent implements OnInit, OnDestroy {
  diasFuncionamentos?: IDiasFuncionamento[];
  eventSubscriber?: Subscription;

  constructor(
    protected diasFuncionamentoService: DiasFuncionamentoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.diasFuncionamentoService
      .query()
      .subscribe((res: HttpResponse<IDiasFuncionamento[]>) => (this.diasFuncionamentos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDiasFuncionamentos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDiasFuncionamento): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDiasFuncionamentos(): void {
    this.eventSubscriber = this.eventManager.subscribe('diasFuncionamentoListModification', () => this.loadAll());
  }

  delete(diasFuncionamento: IDiasFuncionamento): void {
    const modalRef = this.modalService.open(DiasFuncionamentoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.diasFuncionamento = diasFuncionamento;
  }
}
