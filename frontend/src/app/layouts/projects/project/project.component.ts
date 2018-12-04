import {Component, OnInit} from '@angular/core';
import {ProjectsService} from './projects.service';
import {Project} from './models/Project';

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
    projects: Array<Project>;
    activeProjects: Array<Project>;
    searchStr = '';
    onlyMyProjects = true;

    constructor(private projectService: ProjectsService) {
    }

    ngOnInit() {
        localStorage.setItem('userId', '1');
        this.projectService.getProjects()
            .subscribe((projects: Array<Project>) => {
                this.projects = projects;
                this.activeProjects = this.projects;
            });
    }

    onSearch(e: string) {
        this.searchStr = e;
    }

    showMyProjects(e: boolean) {
        this.onlyMyProjects = e;
    }

    showMore() {
        this.projectService.getMoreProjects()
            .subscribe((projects: Array<Project>) => {
                this.projects = this.projects.concat(projects);
                this.activeProjects = this.projects;
            });
    }
}
