import {Component, OnInit} from '@angular/core';
import {ProjectsService} from './projects.service';
import {Project} from './models/Project';

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
    projects: Project[] = [
        {name: 'Project: 1', type: 'JavaScript', author: 'Oleh Chepak', author_id: 1, isPrivate: true},
        {name: 'Project: 2', type: 'JQuery', author: 'Another Author', author_id: 2, isPrivate: false},
        {name: 'Project: 3', type: 'Vue', author: 'Another Author', author_id: 2, isPrivate: true},
        {name: 'Project: 4', type: 'JavaScript', author: 'Oleh Chepak', author_id: 1, isPrivate: true},
        {name: 'Project: 5', type: 'JQuery', author: 'Another Author', author_id: 2, isPrivate: false},
        {name: 'Project: 6', type: 'JQuery', author: 'Another Author', author_id: 2, isPrivate: true},
        {name: 'Project: 7', type: 'JavaScript', author: 'Oleh Chepak', author_id: 1, isPrivate: false},
        {name: 'Project: 8', type: 'JQuery', author: 'Oleh Chepak', author_id: 1, isPrivate: false},
        {name: 'Project: 9', type: 'JQuery', author: 'Another Author', author_id: 2, isPrivate: false}
    ];
    activeProjects: Project[] = this.projects;
    searchStr = '';
    onlyMyProjects = true;

    constructor(private projectService: ProjectsService) {
    }

    ngOnInit() {
        localStorage.setItem('userId', '1');
    }

    onSearch(e: string) {
        this.searchStr = e;
    }

    showMyProjects(e: boolean) {
        this.onlyMyProjects = e;
    }
}
