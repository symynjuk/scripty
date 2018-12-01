import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ProjectComponent} from '../project/project.component';
import {AdminPageComponent} from './admin-page.component';
import {AdminSidebarComponent} from './sidebar/sidebar.component';

const routes: Routes = [
	{ path: '', 	component: ProjectComponent },
	// { path: 'admin', loadChildren: AdminSidebarComponent}
];

@NgModule({
	declarations: [],
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class AdminRoutingModule {
}
