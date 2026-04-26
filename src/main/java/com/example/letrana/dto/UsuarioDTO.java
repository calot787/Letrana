package com.example.letrana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String rol;
}
