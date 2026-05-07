import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroService } from '../libro.service';
import { LibroDTO } from '../libro/libro.dto';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar',
  imports: [FormsModule, CommonModule],
  templateUrl: './editar.component.html',
  styleUrl: './editar.component.css'
})
export class EditarComponent {
libro: LibroDTO = {
    id: undefined,
    titulo: '',
    autor: '',
    genero: '',
    precio: 0,
    //imagen: ''
  };

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

  constructor(
    private route: ActivatedRoute,
    private libroService: LibroService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.libroService.obtener(id).subscribe({
      next: (data) => {
        this.libro = data;
      },
      error: () => {
        console.error('Error cargando libro');
      }
    });
  }

  guardarCambios(): void {
    if (!this.libro.id) return;

    this.libroService.modificar(this.libro.id, this.libro).subscribe({
      next: () => {
        this.router.navigate(['/libro']);
      },
      error: () => {
        console.error('Error actualizando libro');
      }
    });
  }
}
