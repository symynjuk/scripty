import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    authorized: boolean;

    constructor() {
    }

    ngOnInit() {
        this.authorized = false;
    }

    singIn() {
        this.authorized = true;
    }

    logOut() {
        this.authorized = false;
    }

}
