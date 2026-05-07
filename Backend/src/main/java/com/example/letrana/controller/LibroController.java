package com.example.letrana.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.letrana.dto.LibroDTO;
import com.example.letrana.service.LibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<LibroDTO> registrar(@RequestBody LibroDTO dto) {
        LibroDTO creado = libroService.registrar(dto);
        return ResponseEntity.ok(creado);
    }

    // MODIFICAR
    @PutMapping("/modificar/{id}")
    public ResponseEntity<LibroDTO> modificar(@PathVariable Long id, @RequestBody LibroDTO dto) {
        LibroDTO actualizado = libroService.modificar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // BORRAR
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        libroService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    // OBTENER POR ID (usa el for del servicio)
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtener(@PathVariable Long id) {
        LibroDTO dto = libroService.obtener(id);
        return ResponseEntity.ok(dto);
    }

    // OBTENER TODOS
    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerTodos() {
        List<LibroDTO> lista = libroService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    

}
