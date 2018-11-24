import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Project} from '../models/Project';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-project-edit-dialog',
  templateUrl: './project-edit-dialog.component.html',
  styleUrls: ['./project-edit-dialog.component.scss']
})
export class ProjectEditDialogComponent {
  form: FormGroup;
  projectTypes = ['JavaScript', 'JQuery', 'Vue'];

  constructor(
    public dialogRef: MatDialogRef<ProjectEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private project: Project) {
    this.form = new FormGroup({
      name: new FormControl(this.project.name, [Validators.required]),
      type: new FormControl(this.project.type, [Validators.required]),
      isPrivate: new FormControl(this.project.isPrivate ? 'true' : 'false', [Validators.required])
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {
    console.log(this.form.value);
    this.dialogRef.close();
  }

}
