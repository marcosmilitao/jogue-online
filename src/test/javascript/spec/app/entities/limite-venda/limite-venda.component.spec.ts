import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { LimiteVendaComponent } from 'app/entities/limite-venda/limite-venda.component';
import { LimiteVendaService } from 'app/entities/limite-venda/limite-venda.service';
import { LimiteVenda } from 'app/shared/model/limite-venda.model';

describe('Component Tests', () => {
  describe('LimiteVenda Management Component', () => {
    let comp: LimiteVendaComponent;
    let fixture: ComponentFixture<LimiteVendaComponent>;
    let service: LimiteVendaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LimiteVendaComponent]
      })
        .overrideTemplate(LimiteVendaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LimiteVendaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LimiteVendaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LimiteVenda(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.limiteVendas && comp.limiteVendas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
