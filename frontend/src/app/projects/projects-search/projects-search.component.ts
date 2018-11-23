import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-projects-search',
  templateUrl: './projects-search.component.html',
  styleUrls: ['./projects-search.component.scss']
})
export class ProjectsSearchComponent implements OnInit {
  searchStr = '';
  @Output() search = new EventEmitter<string>();
  onlyMyProjects = true;
  @Output() myProjectsCheck = new EventEmitter<boolean>();

  onChange() {
    this.search.emit(this.searchStr);
  }

  showMyProjects() {
    this.myProjectsCheck.emit(this.onlyMyProjects);
  }

  constructor() { }

  ngOnInit() {
  }

}
