package com.example.letrana.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.letrana.dto.UsuarioDTO;
import com.example.letrana.exception.ConflictoException;
import com.example.letrana.exception.RecursoNoEncontradoException;
import com.example.letrana.model.Usuario;
import com.example.letrana.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private  UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder ){
        this.usuarioRepository=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public UsuarioDTO registrar(UsuarioDTO dto){
        Optional<Usuario> opt=usuarioRepository.findByEmail(dto.getEmail());
        if (!opt.isEmpty()) {
            throw new ConflictoException("el email ya esta registrado");
        }
        Usuario u=dtoToEntity(dto);
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRol("USER");
        usuarioRepository.save(u);
        return entityTodto(u);

    }
    public UsuarioDTO getUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null) {
            throw new RecursoNoEncontradoException("No hay usuario autenticado");
        }

        String email = auth.getName();

        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isEmpty()) {
            throw new RecursoNoEncontradoException("Usuario no encontrado");
        }

        Usuario usuario = optionalUsuario.get();

        return entityTodto(usuario);
    }

    public Usuario dtoToEntity(UsuarioDTO dto) {
        Usuario u = new Usuario(
                dto.getId(),
                dto.getNombre(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRol()
        );

        return u;
    }
    public UsuarioDTO entityTodto(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getEmail(),
                u.getPassword(),
                u.getRol()
        );

        return dto;
    }

}
