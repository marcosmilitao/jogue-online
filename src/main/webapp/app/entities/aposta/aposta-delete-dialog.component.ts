import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAposta } from 'app/shared/model/aposta.model';
import { ApostaService } from './aposta.service';

@Component({
  templateUrl: './aposta-delete-dialog.component.html'
})
export class ApostaDeleteDialogComponent {
  aposta?: IAposta;

  constructor(protected apostaService: ApostaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.apostaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('apostaListModification');
      this.activeModal.close();
    });
  }
}
