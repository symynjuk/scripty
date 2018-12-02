import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../models/Project';
import {MatDialog} from '@angular/material';
import {ProjectEditDialogComponent} from '../project-edit-dialog/project-edit-dialog.component';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.scss']
})
export class ProjectEditComponent implements OnInit {
  @Input() project: Project;
  isAccess = false;

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
    this.isAccess = +localStorage.getItem('userId') === this.project.author_id;
  }

  openDialog() {
    this.dialog.open(ProjectEditDialogComponent, {
      maxWidth: '100%',
      data: this.project
    });
  }
}
