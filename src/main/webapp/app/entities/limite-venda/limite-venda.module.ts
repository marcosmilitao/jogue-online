import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { LimiteVendaComponent } from './limite-venda.component';
import { LimiteVendaDetailComponent } from './limite-venda-detail.component';
import { LimiteVendaUpdateComponent } from './limite-venda-update.component';
import { LimiteVendaDeleteDialogComponent } from './limite-venda-delete-dialog.component';
import { limiteVendaRoute } from './limite-venda.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(limiteVendaRoute)],
  declarations: [LimiteVendaComponent, LimiteVendaDetailComponent, LimiteVendaUpdateComponent, LimiteVendaDeleteDialogComponent],
  entryComponents: [LimiteVendaDeleteDialogComponent]
})
export class JogueOnlineLimiteVendaModule {}
