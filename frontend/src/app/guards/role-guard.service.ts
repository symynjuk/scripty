import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {AuthService} from "../services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(
      private _authService: AuthService,
      private _router: Router,
  ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        const user = this._authService.decode();

        if (user.role === route.data.role) {
            return true;
        }

        this._router.navigate(['/404']);
        return false;
    }
}
