import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LimiteVendaService } from 'app/entities/limite-venda/limite-venda.service';
import { ILimiteVenda, LimiteVenda } from 'app/shared/model/limite-venda.model';

describe('Service Tests', () => {
  describe('LimiteVenda Service', () => {
    let injector: TestBed;
    let service: LimiteVendaService;
    let httpMock: HttpTestingController;
    let elemDefault: ILimiteVenda;
    let expectedResult: ILimiteVenda | ILimiteVenda[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LimiteVendaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new LimiteVenda(0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a LimiteVenda', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new LimiteVenda()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LimiteVenda', () => {
        const returnedFromService = Object.assign(
          {
            codigoRevendedor: 1,
            vendaDia: 1,
            debitoAtual: 1,
            limite: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of LimiteVenda', () => {
        const returnedFromService = Object.assign(
          {
            codigoRevendedor: 1,
            vendaDia: 1,
            debitoAtual: 1,
            limite: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a LimiteVenda', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
