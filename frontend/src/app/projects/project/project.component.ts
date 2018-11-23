import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../models/Project';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  @Input() project: Project;
  projectTypes = ['JavaScript', 'JQuery', 'Vue'];
  isActive = false;
  iLikeIt = false;

  constructor() {
  }

  ngOnInit() {
    this.isActive = this.project.author === localStorage.getItem('userName');
  }

  likeIt() {
    this.iLikeIt = !this.iLikeIt;
  }

  onSubmit() {
    console.log('submitted');
  }
}
