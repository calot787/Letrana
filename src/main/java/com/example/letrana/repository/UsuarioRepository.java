package com.example.letrana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letrana.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
