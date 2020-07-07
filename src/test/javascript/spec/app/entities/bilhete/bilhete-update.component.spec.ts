import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { BilheteUpdateComponent } from 'app/entities/bilhete/bilhete-update.component';
import { BilheteService } from 'app/entities/bilhete/bilhete.service';
import { Bilhete } from 'app/shared/model/bilhete.model';

describe('Component Tests', () => {
  describe('Bilhete Management Update Component', () => {
    let comp: BilheteUpdateComponent;
    let fixture: ComponentFixture<BilheteUpdateComponent>;
    let service: BilheteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BilheteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BilheteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BilheteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BilheteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bilhete(123);
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
        const entity = new Bilhete();
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
