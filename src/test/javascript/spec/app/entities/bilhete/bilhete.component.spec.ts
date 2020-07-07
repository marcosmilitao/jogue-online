import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { BilheteComponent } from 'app/entities/bilhete/bilhete.component';
import { BilheteService } from 'app/entities/bilhete/bilhete.service';
import { Bilhete } from 'app/shared/model/bilhete.model';

describe('Component Tests', () => {
  describe('Bilhete Management Component', () => {
    let comp: BilheteComponent;
    let fixture: ComponentFixture<BilheteComponent>;
    let service: BilheteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BilheteComponent]
      })
        .overrideTemplate(BilheteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BilheteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BilheteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Bilhete(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bilhetes && comp.bilhetes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
