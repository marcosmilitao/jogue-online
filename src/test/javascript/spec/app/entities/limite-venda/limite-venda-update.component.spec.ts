import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { LimiteVendaUpdateComponent } from 'app/entities/limite-venda/limite-venda-update.component';
import { LimiteVendaService } from 'app/entities/limite-venda/limite-venda.service';
import { LimiteVenda } from 'app/shared/model/limite-venda.model';

describe('Component Tests', () => {
  describe('LimiteVenda Management Update Component', () => {
    let comp: LimiteVendaUpdateComponent;
    let fixture: ComponentFixture<LimiteVendaUpdateComponent>;
    let service: LimiteVendaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [LimiteVendaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LimiteVendaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LimiteVendaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LimiteVendaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LimiteVenda(123);
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
        const entity = new LimiteVenda();
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
