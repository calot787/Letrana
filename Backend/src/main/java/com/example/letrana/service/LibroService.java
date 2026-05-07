package com.example.letrana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.letrana.dto.LibroDTO;
import com.example.letrana.exception.ConflictoException;
import com.example.letrana.exception.RecursoNoEncontradoException;
import com.example.letrana.model.Libro;
import com.example.letrana.repository.LibroRepository;

@Service
public class LibroService {

    private  LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // REGISTRAR
    public LibroDTO registrar(LibroDTO dto) {

        Optional<Libro> opt = libroRepository.findByTitulo(dto.getTitulo());
        if (opt.isPresent()) {
            throw new ConflictoException("El libro ya existe");
        }

        Libro libro = dtoToEntity(dto);
        libroRepository.save(libro);

        return entityToDto(libro);
    }

    // MODIFICAR
    public LibroDTO modificar(Long id, LibroDTO dto) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado"));

        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setPrecio(dto.getPrecio());

        libroRepository.save(libro);

        return entityToDto(libro);
    }

    // BORRAR
    public void borrar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Libro no encontrado");
        }
        libroRepository.deleteById(id);
    }

    // OBTENER
    public LibroDTO obtener(Long id) {

        List<Libro> lista = libroRepository.findAll();

        for (Libro l : lista) {
            if (l.getId().equals(id)) {
                return entityToDto(l);
            }
        }

        throw new RecursoNoEncontradoException("Libro no encontrado");
    }

    // OBTENER TODOS
    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    // DTO → ENTITY
    public Libro dtoToEntity(LibroDTO dto) {
        return new Libro(
                dto.getId(),
                dto.getTitulo(),
                dto.getAutor(),
                dto.getPrecio()
        );
    }

    // ENTITY → DTO
    public LibroDTO entityToDto(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getPrecio()
        );
    }
}
