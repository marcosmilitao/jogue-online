import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { ModalidadeUpdateComponent } from 'app/entities/modalidade/modalidade-update.component';
import { ModalidadeService } from 'app/entities/modalidade/modalidade.service';
import { Modalidade } from 'app/shared/model/modalidade.model';

describe('Component Tests', () => {
  describe('Modalidade Management Update Component', () => {
    let comp: ModalidadeUpdateComponent;
    let fixture: ComponentFixture<ModalidadeUpdateComponent>;
    let service: ModalidadeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [ModalidadeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModalidadeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModalidadeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModalidadeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Modalidade(123);
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
        const entity = new Modalidade();
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
