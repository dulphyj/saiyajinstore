import { Component, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../../services/authentication.service';
import { Userdto } from '../../../common/userdto';
import { SessionStorageService } from '../../../services/session-storage.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  imports: [HeaderUserComponent, FormsModule, RouterModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  registerLink: string = '/register';
  ;

  constructor(private authentication: AuthenticationService, private sesionStorage: SessionStorageService, private router: Router, private toast: ToastrService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    let userDto = new Userdto(this.email, this.password);
    this.authentication.login(userDto).subscribe(
      response => {
        //this.sesionStorage.setItem('token', { token: response.token });
        this.sesionStorage.setItem('token', response.token);
        this.sesionStorage.setItem('userId', response.id);
        this.sesionStorage.setItem('userType', response.type);
        console.log("token:", response.token);

        if (!response.token) {
          this.toast.error("Invalid credentials", "Error", {
            timeOut: 3000,
            positionClass: 'toast-top-right',
            closeButton: true,
            progressBar: true,
          });
          return;
        }

        if (response.type === 'ADMIN') {
          this.router.navigate(['/admin/products']);
        } else {
          this.router.navigate(['/']);
        }
      },
      error => {
        console.error("Login error:", error);
        this.toast.error("Invalid email or password", "Authentication Failed", {
          timeOut: 3000,
          positionClass: 'toast-top-right',
          closeButton: true,
          progressBar: true,
        });
      }
    );

  }

}
