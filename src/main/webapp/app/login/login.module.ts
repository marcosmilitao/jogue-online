import { NgModule } from '@angular/core';
import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';

import { LoginComponent } from 'app/login/login.component';
import { LOGIN_ROUTE } from 'app/login/login.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild([LOGIN_ROUTE])],
  declarations: [LoginComponent]
})
export class JogueOnlineLoginModule {}
