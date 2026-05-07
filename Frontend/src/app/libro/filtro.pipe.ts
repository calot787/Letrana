import { Pipe, PipeTransform } from '@angular/core';
import { LibroDTO } from './libro/libro.dto';

@Pipe({
  name: 'filtroLibros'
})
export class FiltroPipe implements PipeTransform {

   transform(libro:LibroDTO[]| null |undefined, term: string | null | undefined): LibroDTO[] {
    if(!libro) return []
    if(!term) return libro;
    const t=term.toString().toLowerCase();
    return libro.filter(e=>
      (e.titulo||'').toLowerCase().includes(t)
    );
  }

}
