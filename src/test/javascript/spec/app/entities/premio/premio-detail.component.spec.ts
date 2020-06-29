import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { PremioDetailComponent } from 'app/entities/premio/premio-detail.component';
import { Premio } from 'app/shared/model/premio.model';

describe('Component Tests', () => {
  describe('Premio Management Detail Component', () => {
    let comp: PremioDetailComponent;
    let fixture: ComponentFixture<PremioDetailComponent>;
    const route = ({ data: of({ premio: new Premio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [PremioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PremioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PremioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load premio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.premio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
