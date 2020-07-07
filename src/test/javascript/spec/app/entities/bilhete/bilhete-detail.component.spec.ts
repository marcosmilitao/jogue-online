import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { BilheteDetailComponent } from 'app/entities/bilhete/bilhete-detail.component';
import { Bilhete } from 'app/shared/model/bilhete.model';

describe('Component Tests', () => {
  describe('Bilhete Management Detail Component', () => {
    let comp: BilheteDetailComponent;
    let fixture: ComponentFixture<BilheteDetailComponent>;
    const route = ({ data: of({ bilhete: new Bilhete(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BilheteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BilheteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BilheteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bilhete on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bilhete).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
