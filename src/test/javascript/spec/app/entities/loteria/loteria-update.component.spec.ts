import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { LoteriaUpdateComponent } from 'app/entities/loteria/loteria-update.component';
import { LoteriaService } from 'app/entities/loteria/loteria.service';
import { Loteria } from 'app/shared/model/loteria.model';

describe('Component Tests', () => {
  describe('Loteria Management Update Component', () => {
    let comp: LoteriaUpdateComponent;
    let fixture: ComponentFixture<LoteriaUpdateComponent>;
    let service: LoteriaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LoteriaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LoteriaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LoteriaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LoteriaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Loteria(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Loteria();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
