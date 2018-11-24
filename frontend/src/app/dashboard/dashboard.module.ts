import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout/layout.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import {RouterModule} from "@angular/router";
import {dashboardsRoutes} from "./dashboard.routes";
import {AuthGuardService} from "../guards/auth-guard.service";
import {RoleGuardService} from "../guards/role-guard.service";

@NgModule({
  declarations: [LayoutComponent, HomeComponent, AdminComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(dashboardsRoutes)
  ],
  providers: [AuthGuardService, RoleGuardService],
})
export class DashboardModule { }
