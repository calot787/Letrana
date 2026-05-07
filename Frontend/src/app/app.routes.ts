import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { InicioComponent } from './inicio/inicio.component';
import { LibroComponent } from './libro/libro.component';
import { RegistrarComponent } from './libro/registrar/registrar.component';
import { EditarComponent } from './libro/editar/editar.component'; // ← IMPORTANTE

export const routes: Routes = [
    { path: '', redirectTo: 'inicio', pathMatch: 'full' },
    { path: 'inicio', component: InicioComponent },
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegistroComponent },
    { path: 'libro', component: LibroComponent },
    { path: 'libros/registrar', component: RegistrarComponent },
    { path: 'libros/editar/:id', component: EditarComponent }
];
