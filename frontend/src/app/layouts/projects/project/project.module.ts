import {NgModule} from '@angular/core';
import {ProjectComponent} from './project.component';
import {CommonModule} from '@angular/common';
import {ProjectsListComponent} from './projects-list/projects-list.component';
import {ProjectsSearchComponent} from './projects-search/projects-search.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from './pipes/filter.pipe';
import {ProjectEditDialogComponent} from './project-edit-dialog/project-edit-dialog.component';
import {ProjectLikeComponent} from './project-like/project-like.component';
import {ProjectCreateDialogComponent} from './project-create-dialog/project-create-dialog.component';
import {MaterialModule} from '../../../material.module';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';

@NgModule({
    declarations: [
        ProjectComponent,
        ProjectsListComponent,
        ProjectsSearchComponent,
        FilterPipe,
        ProjectEditDialogComponent,
        ProjectLikeComponent,
        ProjectCreateDialogComponent,
        ConfirmDialogComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule
    ],
    entryComponents: [
        ProjectEditDialogComponent,
        ProjectCreateDialogComponent,
        ConfirmDialogComponent
    ]
})
export class ProjectModule {
}
