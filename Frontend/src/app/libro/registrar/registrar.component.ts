import { Component } from '@angular/core';
import { LibroDTO } from '../libro/libro.dto';
import { LibroService } from '../libro.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registrar',
  imports: [FormsModule, CommonModule],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent {
  generos: string[] = [
    'Ficción',
    'Clásico',
    'Infantil',
    'Misterio',
    'Romance',
    'Aventura',
    'Terror',
    'Ciencia Ficción'
  ];

  libro: LibroDTO = {
    id: undefined,
    titulo: '',
    autor: '',
    genero: '',
    precio: 0,
    //imagen: ''
  };

  constructor(
    private libroService: LibroService,
    private router: Router
  ) {}

  registrar(): void {
    this.libroService.registrar(this.libro).subscribe(() => {
      this.router.navigate(['/libro']);
    });
  }
}

