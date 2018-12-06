import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';


import {ProjectsRoutingModule} from './projects-routing.module';
import {ProjectsComponent} from './projects.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {ProjectComponent} from './project/project.component';

@NgModule({
	imports: [
		CommonModule,
		ProjectsRoutingModule
	],
	declarations: [
		ProjectsComponent,
		FooterComponent,
		HeaderComponent,
		SidebarComponent,
		ProjectComponent
	]
})
export class ProjectsModule {
}
