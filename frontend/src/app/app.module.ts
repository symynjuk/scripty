import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MaterialModule} from './material-module';
import {TitleService} from './title.service';

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MaterialModule
    ],
    providers: [
        TitleService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
    constructor(titleService: TitleService) {
        titleService.init();
    }
}
