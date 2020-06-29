import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { RevendedorDetailComponent } from 'app/entities/revendedor/revendedor-detail.component';
import { Revendedor } from 'app/shared/model/revendedor.model';

describe('Component Tests', () => {
  describe('Revendedor Management Detail Component', () => {
    let comp: RevendedorDetailComponent;
    let fixture: ComponentFixture<RevendedorDetailComponent>;
    const route = ({ data: of({ revendedor: new Revendedor(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [RevendedorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RevendedorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RevendedorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load revendedor on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.revendedor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
