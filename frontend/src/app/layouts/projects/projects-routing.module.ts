import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ProjectsComponent} from './projects.component';
import {ProjectComponent} from './project/project.component';


const projectRoutes: Routes = [
    {
        path: '',
        component: ProjectsComponent,
        children: [
            {
                path: '',
                component: ProjectComponent,
                data: {title: 'Your project'}
            },
            {
                path: 'project',
                component: ProjectComponent,
                data: {title: 'Your project'}
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(projectRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class ProjectsRoutingModule {
}
