package com.example.letrana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letrana.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

}
