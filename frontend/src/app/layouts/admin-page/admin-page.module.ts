import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';


import {AdminRoutingModule} from './admin-routing.module';
import {AdminPageComponent} from './admin-page.component';
import {AdminHeaderComponent} from './header/header.component';
import {AdminSidebarComponent} from './sidebar/sidebar.component';
import {AdminContentComponent} from './content/content.component';
import {AdminFooterComponent} from './footer/footer.component';

@NgModule({
	declarations: [
		AdminPageComponent,
		AdminHeaderComponent,
		AdminSidebarComponent,
		AdminContentComponent,
		AdminFooterComponent
	],
	imports: [
		CommonModule,
		AdminRoutingModule,
	]
})
export class AdminPageModule {
}
