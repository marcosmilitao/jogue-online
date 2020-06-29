import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RevendedorService } from 'app/entities/revendedor/revendedor.service';
import { IRevendedor, Revendedor } from 'app/shared/model/revendedor.model';

describe('Service Tests', () => {
  describe('Revendedor Service', () => {
    let injector: TestBed;
    let service: RevendedorService;
    let httpMock: HttpTestingController;
    let elemDefault: IRevendedor;
    let expectedResult: IRevendedor | IRevendedor[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RevendedorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Revendedor(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        0,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            data: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Revendedor', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            data: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );

        service.create(new Revendedor()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Revendedor', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefone: 'BBBBBB',
            tipo: 'BBBBBB',
            tipoColetor: 'BBBBBB',
            serialColetor: 'BBBBBB',
            nomeComercial: 'BBBBBB',
            situacao: true,
            saldo: 1,
            senha: 'BBBBBB',
            data: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Revendedor', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefone: 'BBBBBB',
            tipo: 'BBBBBB',
            tipoColetor: 'BBBBBB',
            serialColetor: 'BBBBBB',
            nomeComercial: 'BBBBBB',
            situacao: true,
            saldo: 1,
            senha: 'BBBBBB',
            data: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Revendedor', () => {
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
