import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { ModalidadeComponent } from 'app/entities/modalidade/modalidade.component';
import { ModalidadeService } from 'app/entities/modalidade/modalidade.service';
import { Modalidade } from 'app/shared/model/modalidade.model';

describe('Component Tests', () => {
  describe('Modalidade Management Component', () => {
    let comp: ModalidadeComponent;
    let fixture: ComponentFixture<ModalidadeComponent>;
    let service: ModalidadeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [ModalidadeComponent]
      })
        .overrideTemplate(ModalidadeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModalidadeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModalidadeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Modalidade(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modalidades && comp.modalidades[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
