import { Component, OnInit } from '@angular/core';
import { LibroService } from '../libro/libro.service';
import { LibroDTO } from '../libro/libro/libro.dto';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FiltroPipe } from './filtro.pipe';

@Component({
  selector: 'app-libro',
  templateUrl: './libro.component.html',
  styleUrls: ['./libro.component.css'],
  imports: [FormsModule, CommonModule, RouterLink, FiltroPipe]
})
export class LibroComponent implements OnInit {

  libros: LibroDTO[] = [];
  busqueda: string = '';

  constructor(private libroService: LibroService) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.libroService.obtenerTodos().subscribe(data => {
      this.libros = data;
    });
  }

  //MÉTODO BORRAR
  borrar(id: number | undefined): void {
    if (!id) return;

    if (!confirm('¿Seguro que quieres borrar este libro?')) return;

    this.libroService.borrar(id).subscribe({
      next: () => {
        // Recargar lista después de borrar
        this.cargarTodos();
      },
      error: () => {
        console.error('Error al borrar el libro');
      }
    });
  }

  anadir(libro: LibroDTO): void {
    console.log('Añadir al carrito:', libro);
  }
}
