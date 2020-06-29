import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { CustomUserComponent } from './custom-user.component';
import { CustomUserDetailComponent } from './custom-user-detail.component';
import { CustomUserUpdateComponent } from './custom-user-update.component';
import { CustomUserDeleteDialogComponent } from './custom-user-delete-dialog.component';
import { customUserRoute } from './custom-user.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(customUserRoute)],
  declarations: [CustomUserComponent, CustomUserDetailComponent, CustomUserUpdateComponent, CustomUserDeleteDialogComponent],
  entryComponents: [CustomUserDeleteDialogComponent]
})
export class JogueOnlineCustomUserModule {}
