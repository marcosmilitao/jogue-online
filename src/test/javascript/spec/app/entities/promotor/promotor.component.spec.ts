import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { PromotorComponent } from 'app/entities/promotor/promotor.component';
import { PromotorService } from 'app/entities/promotor/promotor.service';
import { Promotor } from 'app/shared/model/promotor.model';

describe('Component Tests', () => {
  describe('Promotor Management Component', () => {
    let comp: PromotorComponent;
    let fixture: ComponentFixture<PromotorComponent>;
    let service: PromotorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [PromotorComponent]
      })
        .overrideTemplate(PromotorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromotorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromotorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Promotor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.promotors && comp.promotors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
