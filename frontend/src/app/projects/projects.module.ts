import {NgModule} from '@angular/core';
import {ProjectsComponent} from './projects.component';
import {CommonModule} from '@angular/common';
import { ProjectsRoutingModule } from './projects-routing.module';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { ProjectsSearchComponent } from './projects-search/projects-search.component';
import {
  MatButtonModule, MatCardModule, MatCheckboxModule,
  MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatMenuModule,
  MatProgressSpinnerModule, MatSelectModule, MatSortModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import { ProjectComponent } from './project/project.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from './pipes/filter.pipe';

@NgModule({
    declarations: [
        ProjectsComponent,
        ProjectsListComponent,
        ProjectsSearchComponent,
        ProjectComponent,
        FilterPipe
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
      MatCheckboxModule
    ]
})
export class ProjectsModule {}
