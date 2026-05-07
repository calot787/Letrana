package com.example.letrana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private double precio;
    private String genero;
}
