import { Component, OnInit } from '@angular/core';
import {AdminPageComponent} from '../admin-page.component';

@Component({
	selector: 'app-admin-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class AdminHeaderComponent implements OnInit {
	sidebarIsCollapsed: boolean;
	constructor(private AdminParent: AdminPageComponent) { }
	ngOnInit() {
	}
	toggleSidebar () {
		this.AdminParent.toggleSidebar();
		this.sidebarIsCollapsed = this.AdminParent.sidebarIsCollapsed;
	}
}
