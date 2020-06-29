import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAposta } from 'app/shared/model/aposta.model';
import { ApostaService } from './aposta.service';
import { ApostaDeleteDialogComponent } from './aposta-delete-dialog.component';

@Component({
  selector: 'jhi-aposta',
  templateUrl: './aposta.component.html'
})
export class ApostaComponent implements OnInit, OnDestroy {
  apostas?: IAposta[];
  eventSubscriber?: Subscription;

  constructor(protected apostaService: ApostaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.apostaService.query().subscribe((res: HttpResponse<IAposta[]>) => (this.apostas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInApostas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAposta): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInApostas(): void {
    this.eventSubscriber = this.eventManager.subscribe('apostaListModification', () => this.loadAll());
  }

  delete(aposta: IAposta): void {
    const modalRef = this.modalService.open(ApostaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.aposta = aposta;
  }
}
