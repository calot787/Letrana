import { Component } from '@angular/core';
import { AuthServiceService } from '../AuthService/auth-service.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registro',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  nombre: string = '';
  email: string = '';
  password: string = '';
  
  errorMsg: string = '';
  successMsg: string = '';

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  registro() {
    if (!this.nombre || !this.email || !this.password) {
      this.errorMsg = 'Todos los campos son obligatorios';
      this.successMsg = '';
      return;
    }

    this.authService.registro(this.nombre, this.email, this.password)
      .subscribe({
        next: () => {
          this.errorMsg = '';
          this.successMsg = 'Registro completado correctamente';
        },
        error: () => {
          this.successMsg = '';
          this.errorMsg = 'No se pudo registrar. Revisa los datos.';
        }
      });
  }
}
