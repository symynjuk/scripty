import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './layouts/home/sidebar/sidebar.component';
import { HeaderComponent } from './layouts/home/header/header.component';
import { MainComponent } from './layouts/home/main/main.component';
/*Material Components*/
import {MaterialModule} from './material-module';
/*Animations*/
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
/*Admin Layout*/
import { AdminPageComponent } from './layouts/admin/admin-page/admin-page.component';
import { AdminFooterComponent } from './layouts/admin/admin-page/footer/footer.component';
import { AdminHeaderComponent} from './layouts/admin/admin-page/header/header.component';
import { AdminSidebarComponent} from './layouts/admin/admin-page/sidebar/sidebar.component';
import { HomeComponent } from './layouts/home/home.component';


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
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
