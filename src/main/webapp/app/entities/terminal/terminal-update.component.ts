import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITerminal, Terminal } from 'app/shared/model/terminal.model';
import { TerminalService } from './terminal.service';
import { IRevendedor } from 'app/shared/model/revendedor.model';
import { RevendedorService } from 'app/entities/revendedor/revendedor.service';

@Component({
  selector: 'jhi-terminal-update',
  templateUrl: './terminal-update.component.html'
})
export class TerminalUpdateComponent implements OnInit {
  isSaving = false;
  revendedors: IRevendedor[] = [];

  editForm = this.fb.group({
    id: [],
    telefoneChipe: [],
    serial: [null, [Validators.required]],
    menssagem: [],
    senhaComunicacao: [],
    dataInicio: [],
    situacao: [],
    versaoTerminal: [],
    mudaCodigo: [],
    dataEntrada: [],
    numeroFonte: [],
    codigoAutorizacao: [],
    imei: [null, [Validators.required]],
    email: [],
    codigoBanca: [],
    revendedor: []
  });

  constructor(
    protected terminalService: TerminalService,
    protected revendedorService: RevendedorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ terminal }) => {
      if (!terminal.id) {
        const today = moment().startOf('day');
        terminal.dataInicio = today;
        terminal.dataEntrada = today;
      }

      this.updateForm(terminal);

      this.revendedorService.query().subscribe((res: HttpResponse<IRevendedor[]>) => (this.revendedors = res.body || []));
    });
  }

  updateForm(terminal: ITerminal): void {
    this.editForm.patchValue({
      id: terminal.id,
      telefoneChipe: terminal.telefoneChipe,
      serial: terminal.serial,
      menssagem: terminal.menssagem,
      senhaComunicacao: terminal.senhaComunicacao,
      dataInicio: terminal.dataInicio ? terminal.dataInicio.format(DATE_TIME_FORMAT) : null,
      situacao: terminal.situacao,
      versaoTerminal: terminal.versaoTerminal,
      mudaCodigo: terminal.mudaCodigo,
      dataEntrada: terminal.dataEntrada ? terminal.dataEntrada.format(DATE_TIME_FORMAT) : null,
      numeroFonte: terminal.numeroFonte,
      codigoAutorizacao: terminal.codigoAutorizacao,
      imei: terminal.imei,
      email: terminal.email,
      codigoBanca: terminal.codigoBanca,
      revendedor: terminal.revendedor
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const terminal = this.createFromForm();
    if (terminal.id !== undefined) {
      this.subscribeToSaveResponse(this.terminalService.update(terminal));
    } else {
      this.subscribeToSaveResponse(this.terminalService.create(terminal));
    }
  }

  private createFromForm(): ITerminal {
    return {
      ...new Terminal(),
      id: this.editForm.get(['id'])!.value,
      telefoneChipe: this.editForm.get(['telefoneChipe'])!.value,
      serial: this.editForm.get(['serial'])!.value,
      menssagem: this.editForm.get(['menssagem'])!.value,
      senhaComunicacao: this.editForm.get(['senhaComunicacao'])!.value,
      dataInicio: this.editForm.get(['dataInicio'])!.value ? moment(this.editForm.get(['dataInicio'])!.value, DATE_TIME_FORMAT) : undefined,
      situacao: this.editForm.get(['situacao'])!.value,
      versaoTerminal: this.editForm.get(['versaoTerminal'])!.value,
      mudaCodigo: this.editForm.get(['mudaCodigo'])!.value,
      dataEntrada: this.editForm.get(['dataEntrada'])!.value
        ? moment(this.editForm.get(['dataEntrada'])!.value, DATE_TIME_FORMAT)
        : undefined,
      numeroFonte: this.editForm.get(['numeroFonte'])!.value,
      codigoAutorizacao: this.editForm.get(['codigoAutorizacao'])!.value,
      imei: this.editForm.get(['imei'])!.value,
      email: this.editForm.get(['email'])!.value,
      codigoBanca: this.editForm.get(['codigoBanca'])!.value,
      revendedor: this.editForm.get(['revendedor'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITerminal>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRevendedor): any {
    return item.id;
  }
}
