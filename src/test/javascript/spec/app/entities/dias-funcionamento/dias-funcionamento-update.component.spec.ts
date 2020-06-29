import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { DiasFuncionamentoUpdateComponent } from 'app/entities/dias-funcionamento/dias-funcionamento-update.component';
import { DiasFuncionamentoService } from 'app/entities/dias-funcionamento/dias-funcionamento.service';
import { DiasFuncionamento } from 'app/shared/model/dias-funcionamento.model';

describe('Component Tests', () => {
  describe('DiasFuncionamento Management Update Component', () => {
    let comp: DiasFuncionamentoUpdateComponent;
    let fixture: ComponentFixture<DiasFuncionamentoUpdateComponent>;
    let service: DiasFuncionamentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [DiasFuncionamentoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DiasFuncionamentoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DiasFuncionamentoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DiasFuncionamentoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DiasFuncionamento(123);
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
        const entity = new DiasFuncionamento();
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
