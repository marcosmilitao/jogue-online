import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ModalidadeService } from 'app/entities/modalidade/modalidade.service';
import { IModalidade, Modalidade } from 'app/shared/model/modalidade.model';

describe('Service Tests', () => {
  describe('Modalidade Service', () => {
    let injector: TestBed;
    let service: ModalidadeService;
    let httpMock: HttpTestingController;
    let elemDefault: IModalidade;
    let expectedResult: IModalidade | IModalidade[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ModalidadeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Modalidade(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, false, false, 'AAAAAAA', false, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Modalidade', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Modalidade()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Modalidade', () => {
        const returnedFromService = Object.assign(
          {
            codigoModalidade: 'BBBBBB',
            nome: 'BBBBBB',
            menorPalpite: 1,
            maiorPalpite: 1,
            qtdePalpites: 1,
            qtdeMinimaPalpites: 1,
            qtdeCaracteres: 1,
            qtdeMinimaCaracteres: 1,
            menorValor: 1,
            maiorValor: 1,
            maiorValorExcessao: true,
            repeticao: true,
            mascara: 'BBBBBB',
            palpiteMultiplo: true,
            palpiteMultiploTerminal: true,
            ordenar: true,
            permitePalpiteAleatorio: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Modalidade', () => {
        const returnedFromService = Object.assign(
          {
            codigoModalidade: 'BBBBBB',
            nome: 'BBBBBB',
            menorPalpite: 1,
            maiorPalpite: 1,
            qtdePalpites: 1,
            qtdeMinimaPalpites: 1,
            qtdeCaracteres: 1,
            qtdeMinimaCaracteres: 1,
            menorValor: 1,
            maiorValor: 1,
            maiorValorExcessao: true,
            repeticao: true,
            mascara: 'BBBBBB',
            palpiteMultiplo: true,
            palpiteMultiploTerminal: true,
            ordenar: true,
            permitePalpiteAleatorio: true
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

      it('should delete a Modalidade', () => {
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
