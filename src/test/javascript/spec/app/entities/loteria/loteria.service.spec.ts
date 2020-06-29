import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LoteriaService } from 'app/entities/loteria/loteria.service';
import { ILoteria, Loteria } from 'app/shared/model/loteria.model';

describe('Service Tests', () => {
  describe('Loteria Service', () => {
    let injector: TestBed;
    let service: LoteriaService;
    let httpMock: HttpTestingController;
    let elemDefault: ILoteria;
    let expectedResult: ILoteria | ILoteria[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LoteriaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Loteria(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 0, currentDate, 0, 'AAAAAAA', 0, 0, false, 'AAAAAAA');
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

      it('should create a Loteria', () => {
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

        service.create(new Loteria()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Loteria', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            horaEncerramento: 'BBBBBB',
            premiacaoInicio: 'BBBBBB',
            status: true,
            limitePremio: 1,
            data: currentDate.format(DATE_TIME_FORMAT),
            codigo: 1,
            descricao: 'BBBBBB',
            hora: 1,
            minuto: 1,
            disponivel: true,
            descricaoCompleta: 'BBBBBB'
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

      it('should return a list of Loteria', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            horaEncerramento: 'BBBBBB',
            premiacaoInicio: 'BBBBBB',
            status: true,
            limitePremio: 1,
            data: currentDate.format(DATE_TIME_FORMAT),
            codigo: 1,
            descricao: 'BBBBBB',
            hora: 1,
            minuto: 1,
            disponivel: true,
            descricaoCompleta: 'BBBBBB'
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

      it('should delete a Loteria', () => {
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
