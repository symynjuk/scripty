import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminPageComponent } from './layouts/admin-page/admin-page.component';
import {ProjectComponent} from './layouts/project/project.component';

const routes: Routes = [
	{ path: '', 	component: ProjectComponent },
	{ path: 'admin', component: AdminPageComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
