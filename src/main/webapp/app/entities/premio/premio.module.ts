import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { PremioComponent } from './premio.component';
import { PremioDetailComponent } from './premio-detail.component';
import { PremioUpdateComponent } from './premio-update.component';
import { PremioDeleteDialogComponent } from './premio-delete-dialog.component';
import { premioRoute } from './premio.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(premioRoute)],
  declarations: [PremioComponent, PremioDetailComponent, PremioUpdateComponent, PremioDeleteDialogComponent],
  entryComponents: [PremioDeleteDialogComponent]
})
export class JogueOnlinePremioModule {}
