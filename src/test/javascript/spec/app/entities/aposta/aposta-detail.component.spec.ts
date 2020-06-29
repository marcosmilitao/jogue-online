import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { ApostaDetailComponent } from 'app/entities/aposta/aposta-detail.component';
import { Aposta } from 'app/shared/model/aposta.model';

describe('Component Tests', () => {
  describe('Aposta Management Detail Component', () => {
    let comp: ApostaDetailComponent;
    let fixture: ComponentFixture<ApostaDetailComponent>;
    const route = ({ data: of({ aposta: new Aposta(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [ApostaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApostaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApostaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load aposta on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aposta).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
