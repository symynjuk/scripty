import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AdminPageComponent } from './layouts/admin/admin-page/admin-page.component';
import {HomeComponent} from './layouts/home/home.component';
import {ProjectComponent} from './project/project.component';

const routes: Routes = [
  // { path: '', component:  },
	{ path: '', 	component: ProjectComponent },
  { path: 'admin', component: AdminPageComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
