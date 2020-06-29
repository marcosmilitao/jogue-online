import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { BancaComponent } from './banca.component';
import { BancaDetailComponent } from './banca-detail.component';
import { BancaUpdateComponent } from './banca-update.component';
import { BancaDeleteDialogComponent } from './banca-delete-dialog.component';
import { bancaRoute } from './banca.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(bancaRoute)],
  declarations: [BancaComponent, BancaDetailComponent, BancaUpdateComponent, BancaDeleteDialogComponent],
  entryComponents: [BancaDeleteDialogComponent]
})
export class JogueOnlineBancaModule {}
