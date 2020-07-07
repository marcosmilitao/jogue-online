import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { BilheteComponent } from './bilhete.component';
import { BilheteDetailComponent } from './bilhete-detail.component';
import { BilheteUpdateComponent } from './bilhete-update.component';
import { BilheteDeleteDialogComponent } from './bilhete-delete-dialog.component';
import { bilheteRoute } from './bilhete.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(bilheteRoute)],
  declarations: [BilheteComponent, BilheteDetailComponent, BilheteUpdateComponent, BilheteDeleteDialogComponent],
  entryComponents: [BilheteDeleteDialogComponent]
})
export class JogueOnlineBilheteModule {}
