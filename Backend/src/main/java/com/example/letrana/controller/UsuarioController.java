package com.example.letrana.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.letrana.dto.UsuarioDTO;
import com.example.letrana.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @PostMapping("/registro") 
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO dto) {

        UsuarioDTO usuario = usuarioService.registrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}
