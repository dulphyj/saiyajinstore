import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from '../../services/session-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit {

  constructor(private sessionStorage: SessionStorageService, private router: Router) { }

  ngOnInit(): void {
    this.sessionStorage.removeItem('token');
    this.sessionStorage.removeItem('userId');
    this.router.navigate(['/login']);
  }

}
