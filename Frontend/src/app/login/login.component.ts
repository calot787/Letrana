import { Component } from '@angular/core';
import { AuthServiceService } from '../AuthService/auth-service.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  errorMsg: string = '';

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  login() {
    if (!this.email || !this.password) {
      this.errorMsg = 'Debes rellenar todos los campos';
      return;
    }

    this.authService.login(this.email, this.password).subscribe({
      next: (token: string) => {

        //Guardar token en sessionStorage
        sessionStorage.setItem('token', token);

        // Pedir perfil al backend
        this.authService.perfil().subscribe({
          next: (usuario) => {

            // Guardar usuario en sessionStorage
            sessionStorage.setItem('usuario', JSON.stringify(usuario));

            //  Redirigir
            this.errorMsg = '';
            this.router.navigate(['/inicio']);
          },
          error: () => {
            this.errorMsg = 'No se pudo obtener el perfil del usuario';
          }
        });
      },
      error: () => {
        this.errorMsg = 'Credenciales incorrectas';
      }
    });
  }
}
