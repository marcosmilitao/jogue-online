import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { LoteriaComponent } from 'app/entities/loteria/loteria.component';
import { LoteriaService } from 'app/entities/loteria/loteria.service';
import { Loteria } from 'app/shared/model/loteria.model';

describe('Component Tests', () => {
  describe('Loteria Management Component', () => {
    let comp: LoteriaComponent;
    let fixture: ComponentFixture<LoteriaComponent>;
    let service: LoteriaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LoteriaComponent]
      })
        .overrideTemplate(LoteriaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LoteriaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LoteriaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Loteria(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.loterias && comp.loterias[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
