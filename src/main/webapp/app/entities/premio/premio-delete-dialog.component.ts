import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPremio } from 'app/shared/model/premio.model';
import { PremioService } from './premio.service';

@Component({
  templateUrl: './premio-delete-dialog.component.html'
})
export class PremioDeleteDialogComponent {
  premio?: IPremio;

  constructor(protected premioService: PremioService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.premioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('premioListModification');
      this.activeModal.close();
    });
  }
}
