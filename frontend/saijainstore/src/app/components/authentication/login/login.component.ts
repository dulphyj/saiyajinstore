import { Component, OnInit } from '@angular/core';
import { HeaderUserComponent } from "../../header-user/header-user.component";
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../../services/authentication.service';
import { Userdto } from '../../../common/userdto';
import { SessionStorageService } from '../../../services/session-storage.service';

@Component({
  selector: 'app-login',
  imports: [HeaderUserComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';;

  constructor(private authentication: AuthenticationService, private sesionStorage: SessionStorageService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    let userDto = new Userdto(this.email, this.password);
    this.authentication.login(userDto).subscribe(
      token => {
        this.sesionStorage.setItem('token', token);
        console.log("Login successful!", token, userDto.username);
      }
    )
  }

}
