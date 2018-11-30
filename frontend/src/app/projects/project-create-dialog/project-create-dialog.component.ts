import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-project-create-dialog',
  templateUrl: './project-create-dialog.component.html',
  styleUrls: ['./project-create-dialog.component.scss']
})
export class ProjectCreateDialogComponent implements OnInit {
  form: FormGroup;
  projectTypes = ['JavaScript', 'JQuery', 'Vue'];

  constructor(public dialogRef: MatDialogRef<ProjectCreateDialogComponent>) {
    this.form = new FormGroup({
      name: new FormControl('Project Name', [Validators.required]),
      type: new FormControl(this.projectTypes[0], [Validators.required]),
      isPrivate: new FormControl('false', [Validators.required])
    });
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  submit() {
    console.log(this.form.value);
    this.dialogRef.close();
  }

}
