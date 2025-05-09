import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SessionStorageService } from '../../services/session-storage.service';

@Component({
  selector: 'app-header-user',
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './header-user.component.html',
  styleUrl: './header-user.component.css'
})
export class HeaderUserComponent implements OnInit {

  searchTerm: any;
  cartLink: string = '/cart/sumary';
  homeLink: string = '/';
  loginLink: string = '/login';
  token: any;

  constructor(private sessionStorage: SessionStorageService) { }

  ngOnInit(): void {
    this.token = this.sessionStorage.getItem('token');
  }
}
