package com.example.letrana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letrana.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    // Método para buscar un usuario por su email
    Optional<Usuario> findByEmail(String email);
}
