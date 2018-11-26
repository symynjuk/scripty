import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../models/Project';

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.scss']
})
export class ProjectsListComponent implements OnInit {
  @Input() projects: Project[];
  @Input() searchStr: string;
  @Input() onlyMyProjects: boolean;
  displayedColumns: string[] = ['name', 'type', 'author', 'like', 'edit'];

  ngOnInit() {
  }
}
