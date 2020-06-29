import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { ApostaComponent } from 'app/entities/aposta/aposta.component';
import { ApostaService } from 'app/entities/aposta/aposta.service';
import { Aposta } from 'app/shared/model/aposta.model';

describe('Component Tests', () => {
  describe('Aposta Management Component', () => {
    let comp: ApostaComponent;
    let fixture: ComponentFixture<ApostaComponent>;
    let service: ApostaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [ApostaComponent]
      })
        .overrideTemplate(ApostaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApostaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApostaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Aposta(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apostas && comp.apostas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
