import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { LoteriaDetailComponent } from 'app/entities/loteria/loteria-detail.component';
import { Loteria } from 'app/shared/model/loteria.model';

describe('Component Tests', () => {
  describe('Loteria Management Detail Component', () => {
    let comp: LoteriaDetailComponent;
    let fixture: ComponentFixture<LoteriaDetailComponent>;
    const route = ({ data: of({ loteria: new Loteria(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LoteriaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LoteriaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LoteriaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load loteria on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.loteria).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
