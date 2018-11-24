import {NgModule} from '@angular/core';
import {ProjectsComponent} from './projects.component';
import {CommonModule} from '@angular/common';
import { ProjectsRoutingModule } from './projects-routing.module';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { ProjectsSearchComponent } from './projects-search/projects-search.component';
import {
  MatButtonModule, MatCardModule, MatCheckboxModule, MatDialogModule,
  MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatMenuModule,
  MatProgressSpinnerModule, MatSelectModule, MatSortModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from './pipes/filter.pipe';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectEditDialogComponent } from './project-edit-dialog/project-edit-dialog.component';

@NgModule({
    declarations: [
        ProjectsComponent,
        ProjectsListComponent,
        ProjectsSearchComponent,
        FilterPipe,
        ProjectEditComponent,
        ProjectEditDialogComponent
    ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      ProjectsRoutingModule,
      MatCardModule,
      MatProgressSpinnerModule,
      MatMenuModule,
      MatIconModule,
      MatToolbarModule,
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
    ProjectEditDialogComponent
  ]
})
export class ProjectsModule {}
