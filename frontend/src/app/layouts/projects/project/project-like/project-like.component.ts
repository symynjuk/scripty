import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-project-like',
    templateUrl: './project-like.component.html',
    styleUrls: ['./project-like.component.scss']
})
export class ProjectLikeComponent implements OnInit {
    isLiked = false;

    constructor() {
    }

    ngOnInit() {
    }

    likeIt() {
        this.isLiked = !this.isLiked;
    }
}
