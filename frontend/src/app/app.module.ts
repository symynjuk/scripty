import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';
import { MainComponent } from './main/main.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminPageComponent } from './components/layouts/admin/admin-page/admin-page.component';
import { AdminFooterComponent } from './components/layouts/admin/admin-page/footer/footer.component';
import { AdminHeaderComponent} from './components/layouts/admin/admin-page/header/header.component';
import { AdminSidebarComponent} from './components/layouts/admin/admin-page/sidebar/sidebar.component';
import { HomeComponent } from './components/layouts/home/home.component';


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
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
