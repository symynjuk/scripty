import { Component, OnInit } from '@angular/core';
import {ProjectsService} from './projects.service';
import {Project} from './models/Project';


@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {
  projects: Project[] = [
    { name: 'Project: 1', type: 'JavaScript', author: 'Oleh Chepak', isPrivate: true },
    { name: 'Project: 2', type: 'JQuery', author: 'Another Author', isPrivate: false },
    { name: 'Project: 3', type: 'Vue', author: 'Another Author', isPrivate: true },
    { name: 'Project: 4', type: 'JavaScript', author: 'Oleh Chepak', isPrivate: true },
    { name: 'Project: 5', type: 'JQuery', author: 'Another Author', isPrivate: false },
    { name: 'Project: 6', type: 'JQuery', author: 'Another Author', isPrivate: true },
    { name: 'Project: 7', type: 'JavaScript', author: 'Oleh Chepak', isPrivate: false },
    { name: 'Project: 8', type: 'JQuery', author: 'Oleh Chepak', isPrivate: false },
    { name: 'Project: 9', type: 'JQuery', author: 'Another Author', isPrivate: false }
  ];
  activeProjects: Project[] = this.projects;
  searchStr = '';
  onlyMyProjects = true;

  userName = 'Oleh Chepak';

  constructor(private projectService: ProjectsService) { }

  ngOnInit() {
    localStorage.setItem('userName', 'Oleh Chepak');
  }

  onSearch(e: string) {
    this.searchStr = e;
  }

  showMyProjects(e: boolean) {
    this.onlyMyProjects = e;
  }
}
