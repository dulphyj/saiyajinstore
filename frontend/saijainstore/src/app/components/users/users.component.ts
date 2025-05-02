import { Component, OnInit } from '@angular/core';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../common/user';
import { UserService } from '../../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-users',
  imports: [HeaderAdminComponent, CommonModule, FormsModule, RouterModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService, private toast: ToastrService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers()
      .subscribe(users => this.users = users);
  }


  updateUser(user: any) {
    // llamada al servicio para actualizar tipo de usuario
    // después de actualizar:
  }

  deleteUserById(userId: number) {
    console.log(userId);
    this.userService.deleteUser(userId)
      .subscribe({
        next: () => {
          this.getAllUsers();
          this.toast.info('Usuario eliminada exitosamente!', 'Deleted');
        },
        error: (err) => {
          this.toast.error('Error al eliminar el usuario', 'Error');
        }
      })
  }
}
