import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminPageComponent } from './components/layouts/admin/admin-page/admin-page.component';
import { AdminFooterComponent } from './components/layouts/admin/admin-page/footer/footer.component';
import { AdminHeaderComponent} from './components/layouts/admin/admin-page/header/header.component';
import { AdminSidebarComponent} from './components/layouts/admin/admin-page/sidebar/sidebar.component';
import { ProjectComponent } from './project/project.component';
import { FooterComponent } from './project/footer/footer.component';
import {MainComponent} from './project/main/main.component';
import {SidebarComponent} from './project/sidebar/sidebar.component';
import {HeaderComponent} from './project/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HeaderComponent,
    MainComponent,
    AdminPageComponent,
    AdminHeaderComponent,
    AdminFooterComponent,
    AdminSidebarComponent,
    MainComponent,
    ProjectComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
