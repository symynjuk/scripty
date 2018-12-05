import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AdminTicketComponent} from './admin-ticket.component';
import {MaterialModule} from '../material-module';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';

@NgModule({
    declarations: [
        AdminTicketComponent,
        AlertDialogComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule
    ],
    entryComponents: [
        AlertDialogComponent
    ]
})
export class AdminTicketModule {
}
