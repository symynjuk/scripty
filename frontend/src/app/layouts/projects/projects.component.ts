import {Component, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';

@Component({
    selector: 'app-projects',
    templateUrl: './projects.component.html',
    styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {
    isOpen: boolean;


    constructor() {
    }

    ngOnInit() {

    }

    onSidebarIsOpen(isOpen: boolean) {
        this.isOpen = isOpen;
    }


}
