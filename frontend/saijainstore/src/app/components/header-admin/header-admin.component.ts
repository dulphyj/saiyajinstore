import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-admin',
  imports: [RouterModule],
  templateUrl: './header-admin.component.html',
  styleUrl: './header-admin.component.css'
})
export class HeaderAdminComponent {
  homeLink: string = '/';
}
