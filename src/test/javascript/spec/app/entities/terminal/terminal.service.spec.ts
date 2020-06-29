import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TerminalService } from 'app/entities/terminal/terminal.service';
import { ITerminal, Terminal } from 'app/shared/model/terminal.model';

describe('Service Tests', () => {
  describe('Terminal Service', () => {
    let injector: TestBed;
    let service: TerminalService;
    let httpMock: HttpTestingController;
    let elemDefault: ITerminal;
    let expectedResult: ITerminal | ITerminal[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TerminalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Terminal(
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        0,
        0,
        currentDate,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataInicio: currentDate.format(DATE_TIME_FORMAT),
            dataEntrada: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Terminal', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataInicio: currentDate.format(DATE_TIME_FORMAT),
            dataEntrada: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataInicio: currentDate,
            dataEntrada: currentDate
          },
          returnedFromService
        );

        service.create(new Terminal()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Terminal', () => {
        const returnedFromService = Object.assign(
          {
            codigoTerminal: 1,
            telefoneChipe: 1,
            revendedor: 'BBBBBB',
            serialChip: 'BBBBBB',
            menssagem: 'BBBBBB',
            senhaComunicacao: 'BBBBBB',
            dataInicio: currentDate.format(DATE_TIME_FORMAT),
            situacao: true,
            versaoTerminal: 'BBBBBB',
            mudaCodigo: 1,
            numeroTelefoneProvedor: 1,
            dataEntrada: currentDate.format(DATE_TIME_FORMAT),
            numeroFonte: 1,
            codigoAutorizacao: 1,
            serialTerminal: 'BBBBBB',
            email: 'BBBBBB',
            codigoBanca: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataInicio: currentDate,
            dataEntrada: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Terminal', () => {
        const returnedFromService = Object.assign(
          {
            codigoTerminal: 1,
            telefoneChipe: 1,
            revendedor: 'BBBBBB',
            serialChip: 'BBBBBB',
            menssagem: 'BBBBBB',
            senhaComunicacao: 'BBBBBB',
            dataInicio: currentDate.format(DATE_TIME_FORMAT),
            situacao: true,
            versaoTerminal: 'BBBBBB',
            mudaCodigo: 1,
            numeroTelefoneProvedor: 1,
            dataEntrada: currentDate.format(DATE_TIME_FORMAT),
            numeroFonte: 1,
            codigoAutorizacao: 1,
            serialTerminal: 'BBBBBB',
            email: 'BBBBBB',
            codigoBanca: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataInicio: currentDate,
            dataEntrada: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Terminal', () => {
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
