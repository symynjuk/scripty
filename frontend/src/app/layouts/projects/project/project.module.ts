import {NgModule} from '@angular/core';
import {ProjectComponent} from './project.component';
import {CommonModule} from '@angular/common';
import {ProjectsListComponent} from './projects-list/projects-list.component';
import {ProjectsSearchComponent} from './projects-search/projects-search.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from './pipes/filter.pipe';
import {ProjectEditComponent} from './project-edit/project-edit.component';
import {ProjectEditDialogComponent} from './project-edit-dialog/project-edit-dialog.component';
import {ProjectLikeComponent} from './project-like/project-like.component';
import {ProjectCreateDialogComponent} from './project-create-dialog/project-create-dialog.component';
import {MaterialModule} from '../../../material.module';

@NgModule({
    declarations: [
        ProjectComponent,
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
        MaterialModule
    ],
    entryComponents: [
        ProjectEditDialogComponent,
        ProjectCreateDialogComponent
    ]
})
export class ProjectModule {
}
