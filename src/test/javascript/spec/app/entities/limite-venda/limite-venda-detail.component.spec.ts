import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { LimiteVendaDetailComponent } from 'app/entities/limite-venda/limite-venda-detail.component';
import { LimiteVenda } from 'app/shared/model/limite-venda.model';

describe('Component Tests', () => {
  describe('LimiteVenda Management Detail Component', () => {
    let comp: LimiteVendaDetailComponent;
    let fixture: ComponentFixture<LimiteVendaDetailComponent>;
    const route = ({ data: of({ limiteVenda: new LimiteVenda(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LimiteVendaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LimiteVendaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LimiteVendaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load limiteVenda on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.limiteVenda).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
