import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { DiasFuncionamentoComponent } from './dias-funcionamento.component';
import { DiasFuncionamentoDetailComponent } from './dias-funcionamento-detail.component';
import { DiasFuncionamentoUpdateComponent } from './dias-funcionamento-update.component';
import { DiasFuncionamentoDeleteDialogComponent } from './dias-funcionamento-delete-dialog.component';
import { diasFuncionamentoRoute } from './dias-funcionamento.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(diasFuncionamentoRoute)],
  declarations: [
    DiasFuncionamentoComponent,
    DiasFuncionamentoDetailComponent,
    DiasFuncionamentoUpdateComponent,
    DiasFuncionamentoDeleteDialogComponent
  ],
  entryComponents: [DiasFuncionamentoDeleteDialogComponent]
})
export class JogueOnlineDiasFuncionamentoModule {}
