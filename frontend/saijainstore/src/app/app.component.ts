import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UsersComponent } from "./components/users/users.component";
import { HomeComponent } from "./components/home/home.component";

@Component({
  selector: 'app-root',
  imports: [UsersComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'saijainstore';
}
