import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BancaService } from 'app/entities/banca/banca.service';
import { IBanca, Banca } from 'app/shared/model/banca.model';

describe('Service Tests', () => {
  describe('Banca Service', () => {
    let injector: TestBed;
    let service: BancaService;
    let httpMock: HttpTestingController;
    let elemDefault: IBanca;
    let expectedResult: IBanca | IBanca[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BancaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Banca(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0);
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

      it('should create a Banca', () => {
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

        service.create(new Banca()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Banca', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cidade: 'BBBBBB',
            telefone: 'BBBBBB',
            estado: 'BBBBBB',
            limiteDescarga: 1,
            limitePremiacao: 1,
            limiteBaixaAutomatica: 1,
            limiteHorarioEncerramento: 1,
            mensagemPule1: 'BBBBBB',
            mensagemPule2: 'BBBBBB',
            mensagemPule3: 'BBBBBB',
            data: currentDate.format(DATE_TIME_FORMAT),
            bonus: 1
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

      it('should return a list of Banca', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cidade: 'BBBBBB',
            telefone: 'BBBBBB',
            estado: 'BBBBBB',
            limiteDescarga: 1,
            limitePremiacao: 1,
            limiteBaixaAutomatica: 1,
            limiteHorarioEncerramento: 1,
            mensagemPule1: 'BBBBBB',
            mensagemPule2: 'BBBBBB',
            mensagemPule3: 'BBBBBB',
            data: currentDate.format(DATE_TIME_FORMAT),
            bonus: 1
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

      it('should delete a Banca', () => {
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
