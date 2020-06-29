import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { LoteriaComponent } from './loteria.component';
import { LoteriaDetailComponent } from './loteria-detail.component';
import { LoteriaUpdateComponent } from './loteria-update.component';
import { LoteriaDeleteDialogComponent } from './loteria-delete-dialog.component';
import { loteriaRoute } from './loteria.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(loteriaRoute)],
  declarations: [LoteriaComponent, LoteriaDetailComponent, LoteriaUpdateComponent, LoteriaDeleteDialogComponent],
  entryComponents: [LoteriaDeleteDialogComponent]
})
export class JogueOnlineLoteriaModule {}
