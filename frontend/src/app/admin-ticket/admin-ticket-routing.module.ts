import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminTicketComponent} from './admin-ticket.component';

const routes: Routes = [{
  path: 'report',
  component: AdminTicketComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminTicketRoutingModule {}
