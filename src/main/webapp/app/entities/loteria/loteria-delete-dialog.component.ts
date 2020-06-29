import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILoteria } from 'app/shared/model/loteria.model';
import { LoteriaService } from './loteria.service';

@Component({
  templateUrl: './loteria-delete-dialog.component.html'
})
export class LoteriaDeleteDialogComponent {
  loteria?: ILoteria;

  constructor(protected loteriaService: LoteriaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.loteriaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('loteriaListModification');
      this.activeModal.close();
    });
  }
}
