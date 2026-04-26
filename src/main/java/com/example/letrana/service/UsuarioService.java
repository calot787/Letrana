package com.example.letrana.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.letrana.dto.UsuarioDTO;
import com.example.letrana.exception.ConflictoException;
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
        usuarioRepository.save(u);
        return entityTodto(u);

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
