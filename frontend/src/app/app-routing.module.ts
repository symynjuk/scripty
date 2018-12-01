import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {AdminPageComponent} from './layouts/admin-page/admin-page.component';

const routes: Routes = [
	{
		path: 'projects',
		loadChildren: './layouts/projects/projects.module#ProjectsModule',
	},
	{
		path: '',
		redirectTo: '/projects', pathMatch: 'full'
	},
	{
		path: '**', redirectTo: '/projects'
	},
	{
		path: 'admin', component: AdminPageComponent
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes/*, { useHash: true }*/)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
