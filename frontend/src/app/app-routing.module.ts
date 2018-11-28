import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminPageComponent } from './layouts/admin/admin-page/admin-page.component';
import {HomeComponent} from './layouts/home/home.component';

const routes: Routes = [
	{ path: '', component: HomeComponent },
	{ path: 'admin', component: AdminPageComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
