import {NgModule} from '@angular/core';
import {ProjectsComponent} from './projects.component';
import {CommonModule} from '@angular/common';
import { ProjectsRoutingModule } from './projects-routing.module';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { ProjectsSearchComponent } from './projects-search/projects-search.component';
import {
  MatButtonModule, MatCheckboxModule, MatDialogModule,
  MatFormFieldModule, MatInputModule, MatSelectModule, MatSortModule,
  MatTableModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from './pipes/filter.pipe';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectEditDialogComponent } from './project-edit-dialog/project-edit-dialog.component';
import { ProjectLikeComponent } from './project-like/project-like.component';
import { ProjectCreateDialogComponent } from './project-create-dialog/project-create-dialog.component';

@NgModule({
    declarations: [
        ProjectsComponent,
        ProjectsListComponent,
        ProjectsSearchComponent,
        FilterPipe,
        ProjectEditComponent,
        ProjectEditDialogComponent,
        ProjectLikeComponent,
        ProjectCreateDialogComponent
    ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      ProjectsRoutingModule,
      MatButtonModule,
      MatFormFieldModule,
      MatInputModule,
      MatSelectModule,
      MatSortModule,
      MatTableModule,
      MatCheckboxModule,
      MatDialogModule
    ],
  entryComponents: [
    ProjectEditDialogComponent,
    ProjectCreateDialogComponent
  ]
})
export class ProjectsModule {}
