package com.vicv.gestion_cursos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
}
