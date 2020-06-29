import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { DiasFuncionamentoComponent } from 'app/entities/dias-funcionamento/dias-funcionamento.component';
import { DiasFuncionamentoService } from 'app/entities/dias-funcionamento/dias-funcionamento.service';
import { DiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';

describe('Component Tests', () => {
  describe('DiasFuncionamento Management Component', () => {
    let comp: DiasFuncionamentoComponent;
    let fixture: ComponentFixture<DiasFuncionamentoComponent>;
    let service: DiasFuncionamentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [DiasFuncionamentoComponent]
      })
        .overrideTemplate(DiasFuncionamentoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DiasFuncionamentoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DiasFuncionamentoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DiasFuncionamento(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.diasFuncionamentos && comp.diasFuncionamentos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
