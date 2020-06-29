import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { ModalidadeComponent } from './modalidade.component';
import { ModalidadeDetailComponent } from './modalidade-detail.component';
import { ModalidadeUpdateComponent } from './modalidade-update.component';
import { ModalidadeDeleteDialogComponent } from './modalidade-delete-dialog.component';
import { modalidadeRoute } from './modalidade.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(modalidadeRoute)],
  declarations: [ModalidadeComponent, ModalidadeDetailComponent, ModalidadeUpdateComponent, ModalidadeDeleteDialogComponent],
  entryComponents: [ModalidadeDeleteDialogComponent]
})
export class JogueOnlineModalidadeModule {}
