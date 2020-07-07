import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBilhete } from 'app/shared/model/bilhete.model';
import { BilheteService } from './bilhete.service';

@Component({
  templateUrl: './bilhete-delete-dialog.component.html'
})
export class BilheteDeleteDialogComponent {
  bilhete?: IBilhete;

  constructor(protected bilheteService: BilheteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bilheteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bilheteListModification');
      this.activeModal.close();
    });
  }
}
