import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModalidade } from 'app/shared/model/modalidade.model';
import { ModalidadeService } from './modalidade.service';
import { ModalidadeDeleteDialogComponent } from './modalidade-delete-dialog.component';

@Component({
  selector: 'jhi-modalidade',
  templateUrl: './modalidade.component.html'
})
export class ModalidadeComponent implements OnInit, OnDestroy {
  modalidades?: IModalidade[];
  eventSubscriber?: Subscription;

  constructor(protected modalidadeService: ModalidadeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.modalidadeService.query().subscribe((res: HttpResponse<IModalidade[]>) => (this.modalidades = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInModalidades();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModalidade): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModalidades(): void {
    this.eventSubscriber = this.eventManager.subscribe('modalidadeListModification', () => this.loadAll());
  }

  delete(modalidade: IModalidade): void {
    const modalRef = this.modalService.open(ModalidadeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.modalidade = modalidade;
  }
}
