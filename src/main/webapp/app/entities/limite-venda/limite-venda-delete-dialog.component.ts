import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILimiteVenda } from 'app/shared/model/limite-venda.model';
import { LimiteVendaService } from './limite-venda.service';

@Component({
  templateUrl: './limite-venda-delete-dialog.component.html'
})
export class LimiteVendaDeleteDialogComponent {
  limiteVenda?: ILimiteVenda;

  constructor(
    protected limiteVendaService: LimiteVendaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.limiteVendaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('limiteVendaListModification');
      this.activeModal.close();
    });
  }
}
