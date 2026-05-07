import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LibroDTO } from './libro/libro.dto';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = 'http://localhost:8080/api/libros';

  constructor(private http: HttpClient) {}

  // OBTENER TODOS
  obtenerTodos(): Observable<LibroDTO[]> {
    return this.http.get<LibroDTO[]>(`${this.apiUrl}`);
  }

  // OBTENER POR ID
  obtener(id: number): Observable<LibroDTO> {
    return this.http.get<LibroDTO>(`${this.apiUrl}/${id}`);
  }

  // REGISTRAR
  registrar(dto: LibroDTO): Observable<LibroDTO> {
    return this.http.post<LibroDTO>(`${this.apiUrl}/registrar`, dto);
  }

  // MODIFICAR
  modificar(id: number, dto: LibroDTO): Observable<LibroDTO> {
    return this.http.put<LibroDTO>(`${this.apiUrl}/modificar/${id}`, dto);
  }

  // BORRAR
  borrar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/borrar/${id}`);
  }
  //  BUSCAR POR TÍTULO (nuevo)
  buscarPorTitulo(titulo: string): Observable<LibroDTO[]> {
    return this.http.get<LibroDTO[]>(`${this.apiUrl}/buscar?titulo=${titulo}`);
  }
}
