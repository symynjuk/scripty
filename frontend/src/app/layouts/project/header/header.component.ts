import {Component, OnInit} from '@angular/core';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
	authorized: boolean;
	isOpen: boolean;

	constructor() {
	}

	ngOnInit() {
		this.authorized = false;
		this.isOpen = false;
	}

	singIn() {
		this.authorized = true;
	}

	logOut() {
		this.authorized = false;
	}

	openNav() {
		this.isOpen = !this.isOpen;
	}


}
