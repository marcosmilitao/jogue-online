import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JogueOnlineSharedModule } from 'app/shared/shared.module';
import { RevendedorComponent } from './revendedor.component';
import { RevendedorDetailComponent } from './revendedor-detail.component';
import { RevendedorUpdateComponent } from './revendedor-update.component';
import { RevendedorDeleteDialogComponent } from './revendedor-delete-dialog.component';
import { revendedorRoute } from './revendedor.route';

@NgModule({
  imports: [JogueOnlineSharedModule, RouterModule.forChild(revendedorRoute)],
  declarations: [RevendedorComponent, RevendedorDetailComponent, RevendedorUpdateComponent, RevendedorDeleteDialogComponent],
  entryComponents: [RevendedorDeleteDialogComponent]
})
export class JogueOnlineRevendedorModule {}
