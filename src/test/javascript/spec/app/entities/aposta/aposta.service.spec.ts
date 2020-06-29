import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ApostaService } from 'app/entities/aposta/aposta.service';
import { IAposta, Aposta } from 'app/shared/model/aposta.model';

describe('Service Tests', () => {
  describe('Aposta Service', () => {
    let injector: TestBed;
    let service: ApostaService;
    let httpMock: HttpTestingController;
    let elemDefault: IAposta;
    let expectedResult: IAposta | IAposta[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ApostaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Aposta(0, 0, currentDate, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataAposta: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Aposta', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataAposta: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataAposta: currentDate
          },
          returnedFromService
        );

        service.create(new Aposta()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Aposta', () => {
        const returnedFromService = Object.assign(
          {
            codigoJogo: 1,
            dataAposta: currentDate.format(DATE_TIME_FORMAT),
            loteriaNome: 'BBBBBB',
            loteriaCodigo: 1,
            modalide: 'BBBBBB',
            codigoModalidade: 'BBBBBB',
            premio: 'BBBBBB',
            codigoPremio: 1,
            valorJogo: 1,
            codigoBanca: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataAposta: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Aposta', () => {
        const returnedFromService = Object.assign(
          {
            codigoJogo: 1,
            dataAposta: currentDate.format(DATE_TIME_FORMAT),
            loteriaNome: 'BBBBBB',
            loteriaCodigo: 1,
            modalide: 'BBBBBB',
            codigoModalidade: 'BBBBBB',
            premio: 'BBBBBB',
            codigoPremio: 1,
            valorJogo: 1,
            codigoBanca: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataAposta: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Aposta', () => {
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
