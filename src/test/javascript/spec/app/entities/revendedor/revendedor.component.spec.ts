import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JogueOnlineTestModule } from '../../../test.module';
import { RevendedorComponent } from 'app/entities/revendedor/revendedor.component';
import { RevendedorService } from 'app/entities/revendedor/revendedor.service';
import { Revendedor } from 'app/shared/model/revendedor.model';

describe('Component Tests', () => {
  describe('Revendedor Management Component', () => {
    let comp: RevendedorComponent;
    let fixture: ComponentFixture<RevendedorComponent>;
    let service: RevendedorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [RevendedorComponent]
      })
        .overrideTemplate(RevendedorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RevendedorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RevendedorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Revendedor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.revendedors && comp.revendedors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
