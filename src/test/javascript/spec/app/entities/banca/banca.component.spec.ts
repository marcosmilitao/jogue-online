import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { BancaComponent } from 'app/entities/banca/banca.component';
import { BancaService } from 'app/entities/banca/banca.service';
import { Banca } from 'app/shared/model/banca.model';

describe('Component Tests', () => {
  describe('Banca Management Component', () => {
    let comp: BancaComponent;
    let fixture: ComponentFixture<BancaComponent>;
    let service: BancaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BancaComponent]
      })
        .overrideTemplate(BancaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BancaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BancaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Banca(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bancas && comp.bancas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
