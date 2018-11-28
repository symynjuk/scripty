import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-admin-page',
	templateUrl: './admin-page.component.html',
	styleUrls: ['./admin-page.component.scss']
})
export class AdminPageComponent implements OnInit {
	sidebarIsCollapsed = false;
	constructor() { }

	ngOnInit() {
	}
	toggleSidebar() {
		this.sidebarIsCollapsed = !this.sidebarIsCollapsed;
	}
}
