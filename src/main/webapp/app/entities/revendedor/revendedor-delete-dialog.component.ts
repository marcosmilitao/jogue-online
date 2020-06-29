import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRevendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from './revendedor.service';

@Component({
  templateUrl: './revendedor-delete-dialog.component.html'
})
export class RevendedorDeleteDialogComponent {
  revendedor?: IRevendedor;

  constructor(
    protected revendedorService: RevendedorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.revendedorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('revendedorListModification');
      this.activeModal.close();
    });
  }
}
