import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { DiasFuncionamentoDetailComponent } from 'app/entities/dias-funcionamento/dias-funcionamento-detail.component';
import { DiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';

describe('Component Tests', () => {
  describe('DiasFuncionamento Management Detail Component', () => {
    let comp: DiasFuncionamentoDetailComponent;
    let fixture: ComponentFixture<DiasFuncionamentoDetailComponent>;
    const route = ({ data: of({ diasFuncionamento: new DiasFuncionamento(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [DiasFuncionamentoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DiasFuncionamentoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DiasFuncionamentoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load diasFuncionamento on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.diasFuncionamento).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
