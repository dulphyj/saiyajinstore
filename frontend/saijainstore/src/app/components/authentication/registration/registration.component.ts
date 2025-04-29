import { Component, Input, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { CommonModule } from '@angular/common';
import { User } from '../../../common/user';
import { AuthenticationService } from '../../../services/authentication.service';
import { Router } from '@angular/router';
import { UserType } from '../../../common/user-type';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration',
  imports: [HeaderUserComponent, CommonModule, FormsModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit {

  user: User = new User(null, '', '', '', '', '', UserType.USER);

  constructor(private authentication: AuthenticationService, private router: Router, private toast: ToastrService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.user.userName = this.user.email;
    this.authentication.register(this.user).subscribe(
      {
        next: () => {
          this.toast.success('User registered successfully', 'Success');
          console.log('User registered successfully');
          setTimeout(() => this.router.navigate(['/login']), 700);
        },
        error: (error) => {
          console.error('Error registering user', error);
        }
      });
  }
}
