import { Component } from '@angular/core';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { HeaderUserComponent } from "../header-user/header-user.component";
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-error-page',
  imports: [HeaderAdminComponent, HeaderUserComponent, CommonModule, RouterModule],
  templateUrl: './error-page.component.html',
  styleUrl: './error-page.component.css'
})
export class ErrorPageComponent {
  homeLink: String = '/';

}
