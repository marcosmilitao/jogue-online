import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { BancaDetailComponent } from 'app/entities/banca/banca-detail.component';
import { Banca } from 'app/shared/model/banca.model';

describe('Component Tests', () => {
  describe('Banca Management Detail Component', () => {
    let comp: BancaDetailComponent;
    let fixture: ComponentFixture<BancaDetailComponent>;
    const route = ({ data: of({ banca: new Banca(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BancaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BancaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BancaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load banca on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.banca).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
