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
import { IBanca } from 'app/shared/model/banca.model';
import { BancaService } from 'app/entities/banca/banca.service';

@Component({
  selector: 'jhi-terminal-update',
  templateUrl: './terminal-update.component.html'
})
export class TerminalUpdateComponent implements OnInit {
  isSaving = false;
  bancas: IBanca[] = [];

  editForm = this.fb.group({
    id: [],
    codigoTerminal: [null, [Validators.required]],
    telefoneChipe: [],
    revendedor: [],
    serialChip: [null, [Validators.required]],
    menssagem: [],
    senhaComunicacao: [],
    dataInicio: [],
    situacao: [],
    versaoTerminal: [],
    mudaCodigo: [],
    numeroTelefoneProvedor: [],
    dataEntrada: [],
    numeroFonte: [],
    codigoAutorizacao: [],
    serialTerminal: [null, [Validators.required]],
    email: [],
    codigoBanca: [],
    banca: []
  });

  constructor(
    protected terminalService: TerminalService,
    protected bancaService: BancaService,
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

      this.bancaService.query().subscribe((res: HttpResponse<IBanca[]>) => (this.bancas = res.body || []));
    });
  }

  updateForm(terminal: ITerminal): void {
    this.editForm.patchValue({
      id: terminal.id,
      codigoTerminal: terminal.codigoTerminal,
      telefoneChipe: terminal.telefoneChipe,
      revendedor: terminal.revendedor,
      serialChip: terminal.serialChip,
      menssagem: terminal.menssagem,
      senhaComunicacao: terminal.senhaComunicacao,
      dataInicio: terminal.dataInicio ? terminal.dataInicio.format(DATE_TIME_FORMAT) : null,
      situacao: terminal.situacao,
      versaoTerminal: terminal.versaoTerminal,
      mudaCodigo: terminal.mudaCodigo,
      numeroTelefoneProvedor: terminal.numeroTelefoneProvedor,
      dataEntrada: terminal.dataEntrada ? terminal.dataEntrada.format(DATE_TIME_FORMAT) : null,
      numeroFonte: terminal.numeroFonte,
      codigoAutorizacao: terminal.codigoAutorizacao,
      serialTerminal: terminal.serialTerminal,
      email: terminal.email,
      codigoBanca: terminal.codigoBanca,
      banca: terminal.banca
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
      codigoTerminal: this.editForm.get(['codigoTerminal'])!.value,
      telefoneChipe: this.editForm.get(['telefoneChipe'])!.value,
      revendedor: this.editForm.get(['revendedor'])!.value,
      serialChip: this.editForm.get(['serialChip'])!.value,
      menssagem: this.editForm.get(['menssagem'])!.value,
      senhaComunicacao: this.editForm.get(['senhaComunicacao'])!.value,
      dataInicio: this.editForm.get(['dataInicio'])!.value ? moment(this.editForm.get(['dataInicio'])!.value, DATE_TIME_FORMAT) : undefined,
      situacao: this.editForm.get(['situacao'])!.value,
      versaoTerminal: this.editForm.get(['versaoTerminal'])!.value,
      mudaCodigo: this.editForm.get(['mudaCodigo'])!.value,
      numeroTelefoneProvedor: this.editForm.get(['numeroTelefoneProvedor'])!.value,
      dataEntrada: this.editForm.get(['dataEntrada'])!.value
        ? moment(this.editForm.get(['dataEntrada'])!.value, DATE_TIME_FORMAT)
        : undefined,
      numeroFonte: this.editForm.get(['numeroFonte'])!.value,
      codigoAutorizacao: this.editForm.get(['codigoAutorizacao'])!.value,
      serialTerminal: this.editForm.get(['serialTerminal'])!.value,
      email: this.editForm.get(['email'])!.value,
      codigoBanca: this.editForm.get(['codigoBanca'])!.value,
      banca: this.editForm.get(['banca'])!.value
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

  trackById(index: number, item: IBanca): any {
    return item.id;
  }
}
