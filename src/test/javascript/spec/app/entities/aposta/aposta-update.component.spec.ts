import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { ApostaUpdateComponent } from 'app/entities/aposta/aposta-update.component';
import { ApostaService } from 'app/entities/aposta/aposta.service';
import { Aposta } from 'app/shared/model/aposta.model';

describe('Component Tests', () => {
  describe('Aposta Management Update Component', () => {
    let comp: ApostaUpdateComponent;
    let fixture: ComponentFixture<ApostaUpdateComponent>;
    let service: ApostaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [ApostaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApostaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApostaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApostaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Aposta(123);
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
        const entity = new Aposta();
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
