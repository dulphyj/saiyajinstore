import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-user',
  imports: [FormsModule, RouterModule],
  templateUrl: './header-user.component.html',
  styleUrl: './header-user.component.css'
})
export class HeaderUserComponent {
  searchTerm: any;
  cartLink: string = '/cart/sumary';
  homeLink: string = '/';
  onSearch() {
    throw new Error('Method not implemented.');
  }

}
