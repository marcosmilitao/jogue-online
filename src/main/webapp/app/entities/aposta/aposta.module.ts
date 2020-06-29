import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { ApostaComponent } from './aposta.component';
import { ApostaDetailComponent } from './aposta-detail.component';
import { ApostaUpdateComponent } from './aposta-update.component';
import { ApostaDeleteDialogComponent } from './aposta-delete-dialog.component';
import { apostaRoute } from './aposta.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(apostaRoute)],
  declarations: [ApostaComponent, ApostaDetailComponent, ApostaUpdateComponent, ApostaDeleteDialogComponent],
  entryComponents: [ApostaDeleteDialogComponent]
})
export class JogueOnlineApostaModule {}
