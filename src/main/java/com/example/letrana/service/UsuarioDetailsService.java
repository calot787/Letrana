package com.example.letrana.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.letrana.model.Usuario;
import com.example.letrana.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws  UsernameNotFoundException {

        Optional<Usuario> opt = usuarioRepository.findByEmail(email);

        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }

        Usuario u = opt.get();

        return User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRol())
                .build();
    }
}
