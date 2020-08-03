import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'banca',
        loadChildren: () => import('./banca/banca.module').then(m => m.JogueOnlineBancaModule)
      },
      {
        path: 'promotor',
        loadChildren: () => import('./promotor/promotor.module').then(m => m.JogueOnlinePromotorModule)
      },
      {
        path: 'revendedor',
        loadChildren: () => import('./revendedor/revendedor.module').then(m => m.JogueOnlineRevendedorModule)
      },
      {
        path: 'terminal',
        loadChildren: () => import('./terminal/terminal.module').then(m => m.JogueOnlineTerminalModule)
      },
      {
        path: 'modalidade',
        loadChildren: () => import('./modalidade/modalidade.module').then(m => m.JogueOnlineModalidadeModule)
      },
      {
        path: 'premio',
        loadChildren: () => import('./premio/premio.module').then(m => m.JogueOnlinePremioModule)
      },
      {
        path: 'loteria',
        loadChildren: () => import('./loteria/loteria.module').then(m => m.JogueOnlineLoteriaModule)
      },
      {
        path: 'dias-funcionamento',
        loadChildren: () => import('./dias-funcionamento/dias-funcionamento.module').then(m => m.JogueOnlineDiasFuncionamentoModule)
      },
      {
        path: 'aposta',
        loadChildren: () => import('./aposta/aposta.module').then(m => m.JogueOnlineApostaModule)
      },
      {
        path: 'limite-venda',
        loadChildren: () => import('./limite-venda/limite-venda.module').then(m => m.JogueOnlineLimiteVendaModule)
      },
      {
        path: 'custom-user',
        loadChildren: () => import('./custom-user/custom-user.module').then(m => m.JogueOnlineCustomUserModule)
      },
      {
        path: 'bilhete',
        loadChildren: () => import('./bilhete/bilhete.module').then(m => m.JogueOnlineBilheteModule)
      },
      {
        path: 'saldo',
        loadChildren: () => import('./saldo/saldo.module').then(m => m.JogueOnlineSaldoModule)
      },
      {
        path: 'movimentacao',
        loadChildren: () => import('./movimentacao/movimentacao.module').then(m => m.JogueOnlineMovimentacaoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JogueOnlineEntityModule {}
