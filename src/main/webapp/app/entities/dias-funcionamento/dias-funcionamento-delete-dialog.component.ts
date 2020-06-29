import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';
import { DiasFuncionamentoService } from './dias-funcionamento.service';

@Component({
  templateUrl: './dias-funcionamento-delete-dialog.component.html'
})
export class DiasFuncionamentoDeleteDialogComponent {
  diasFuncionamento?: IDiasFuncionamento;

  constructor(
    protected diasFuncionamentoService: DiasFuncionamentoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.diasFuncionamentoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('diasFuncionamentoListModification');
      this.activeModal.close();
    });
  }
}
