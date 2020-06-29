import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModalidade } from 'app/shared/model/modalidade.model';
import { ModalidadeService } from './modalidade.service';

@Component({
  templateUrl: './modalidade-delete-dialog.component.html'
})
export class ModalidadeDeleteDialogComponent {
  modalidade?: IModalidade;

  constructor(
    protected modalidadeService: ModalidadeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modalidadeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modalidadeListModification');
      this.activeModal.close();
    });
  }
}
