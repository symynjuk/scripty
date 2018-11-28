import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    isAuthorized: boolean;
    isOpen: boolean;

    constructor() {
    }

    ngOnInit() {
        this.isAuthorized = false;
        this.isOpen = false;
    }

    signIn() {
        this.isAuthorized = true;
    }

    logOut() {
        this.isAuthorized = false;
    }

    openNav() {
        this.isOpen = !this.isOpen;
    }


}
