import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { BancaUpdateComponent } from 'app/entities/banca/banca-update.component';
import { BancaService } from 'app/entities/banca/banca.service';
import { Banca } from 'app/shared/model/banca.model';

describe('Component Tests', () => {
  describe('Banca Management Update Component', () => {
    let comp: BancaUpdateComponent;
    let fixture: ComponentFixture<BancaUpdateComponent>;
    let service: BancaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [BancaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BancaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BancaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BancaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Banca(123);
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
        const entity = new Banca();
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
