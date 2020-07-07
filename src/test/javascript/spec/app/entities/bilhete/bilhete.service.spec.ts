import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BilheteService } from 'app/entities/bilhete/bilhete.service';
import { IBilhete, Bilhete } from 'app/shared/model/bilhete.model';

describe('Service Tests', () => {
  describe('Bilhete Service', () => {
    let injector: TestBed;
    let service: BilheteService;
    let httpMock: HttpTestingController;
    let elemDefault: IBilhete;
    let expectedResult: IBilhete | IBilhete[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BilheteService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bilhete(0, 0, 0, 0, 'AAAAAAA', 0, 0, 0, 0, 0, currentDate, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataHoraAposta: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bilhete', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataHoraAposta: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataHoraAposta: currentDate
          },
          returnedFromService
        );

        service.create(new Bilhete()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bilhete', () => {
        const returnedFromService = Object.assign(
          {
            numeroBilhete: 1,
            codigoBanca: 1,
            codigoLoteria: 1,
            loteriaNome: 'BBBBBB',
            valorTotalAposta: 1,
            bonusBanca: 1,
            bonusIndividual: 1,
            comicao: 1,
            valorBilhete: 1,
            dataHoraAposta: currentDate.format(DATE_TIME_FORMAT),
            qrcode: 'BBBBBB',
            codigoTerminal: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataHoraAposta: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bilhete', () => {
        const returnedFromService = Object.assign(
          {
            numeroBilhete: 1,
            codigoBanca: 1,
            codigoLoteria: 1,
            loteriaNome: 'BBBBBB',
            valorTotalAposta: 1,
            bonusBanca: 1,
            bonusIndividual: 1,
            comicao: 1,
            valorBilhete: 1,
            dataHoraAposta: currentDate.format(DATE_TIME_FORMAT),
            qrcode: 'BBBBBB',
            codigoTerminal: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataHoraAposta: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bilhete', () => {
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
