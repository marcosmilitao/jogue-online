import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from './banca.service';

@Component({
  templateUrl: './banca-delete-dialog.component.html'
})
export class BancaDeleteDialogComponent {
  banca?: IBanca;

  constructor(protected bancaService: BancaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bancaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bancaListModification');
      this.activeModal.close();
    });
  }
}
