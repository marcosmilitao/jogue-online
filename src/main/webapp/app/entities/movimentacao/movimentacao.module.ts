import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { MovimentacaoComponent } from './movimentacao.component';
import { MovimentacaoDetailComponent } from './movimentacao-detail.component';
import { MovimentacaoUpdateComponent } from './movimentacao-update.component';
import { MovimentacaoDeleteDialogComponent } from './movimentacao-delete-dialog.component';
import { movimentacaoRoute } from './movimentacao.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(movimentacaoRoute)],
  declarations: [MovimentacaoComponent, MovimentacaoDetailComponent, MovimentacaoUpdateComponent, MovimentacaoDeleteDialogComponent],
  entryComponents: [MovimentacaoDeleteDialogComponent]
})
export class JogueOnlineMovimentacaoModule {}
