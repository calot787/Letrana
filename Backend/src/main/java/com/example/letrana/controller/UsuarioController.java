package com.example.letrana.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

     @GetMapping("/perfil")
    public ResponseEntity<?> perfil(Authentication authentication) {

        // 1. Obtener DTO del usuario actual desde el servicio
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioActual();

        // 2. Obtener el rol desde Authentication
        String rol = authentication.getAuthorities().stream()
                .filter(a -> a.getAuthority().startsWith("ROLE_"))
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("USER");

        // 3. Construir el DTO final
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", usuarioDTO.getId());
        dto.put("nombre", usuarioDTO.getNombre());
        dto.put("email", usuarioDTO.getEmail());
        dto.put("rol", rol);

        return ResponseEntity.ok(dto);
    }

}
