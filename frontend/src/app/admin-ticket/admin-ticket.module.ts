import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatButtonModule, MatFormFieldModule, MatInputModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AdminTicketComponent} from './admin-ticket.component';
import {AdminTicketRoutingModule} from './admin-ticket-routing.module';

@NgModule({
  declarations: [
    AdminTicketComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    AdminTicketRoutingModule
  ]
})
export class AdminTicketModule {}
