import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JogueOnlineTestModule } from '../../../test.module';
import { RevendedorUpdateComponent } from 'app/entities/revendedor/revendedor-update.component';
import { RevendedorService } from 'app/entities/revendedor/revendedor.service';
import { Revendedor } from 'app/shared/model/revendedor.model';

describe('Component Tests', () => {
  describe('Revendedor Management Update Component', () => {
    let comp: RevendedorUpdateComponent;
    let fixture: ComponentFixture<RevendedorUpdateComponent>;
    let service: RevendedorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JogueOnlineTestModule],
        declarations: [RevendedorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RevendedorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RevendedorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RevendedorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Revendedor(123);
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
        const entity = new Revendedor();
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
