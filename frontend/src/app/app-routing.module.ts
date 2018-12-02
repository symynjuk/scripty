import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';


const routes: Routes = [
    {
        path: 'projects',
        loadChildren: './layouts/projects/projects.module#ProjectsModule',
        data: {title: 'Projects'}
    },
    {
        path: '',
        redirectTo: '/projects', pathMatch: 'full'
    },
    {
        path: '**', redirectTo: '/projects'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes/*, { useHash: true }*/)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
