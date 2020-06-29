import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICustomUser, CustomUser } from 'app/shared/model/custom-user.model';
import { CustomUserService } from './custom-user.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-custom-user-update',
  templateUrl: './custom-user-update.component.html'
})
export class CustomUserUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    login: [],
    banca: [],
    user: []
  });

  constructor(
    protected customUserService: CustomUserService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customUser }) => {
      this.updateForm(customUser);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(customUser: ICustomUser): void {
    this.editForm.patchValue({
      id: customUser.id,
      login: customUser.login,
      banca: customUser.banca,
      user: customUser.user
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customUser = this.createFromForm();
    if (customUser.id !== undefined) {
      this.subscribeToSaveResponse(this.customUserService.update(customUser));
    } else {
      this.subscribeToSaveResponse(this.customUserService.create(customUser));
    }
  }

  private createFromForm(): ICustomUser {
    return {
      ...new CustomUser(),
      id: this.editForm.get(['id'])!.value,
      login: this.editForm.get(['login'])!.value,
      banca: this.editForm.get(['banca'])!.value,
      user: this.editForm.get(['user'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomUser>>): void {
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
