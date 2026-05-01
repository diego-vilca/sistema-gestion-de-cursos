package com.vicv.gestion_cursos.entities;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {
    private Long id;
    private String nombre;
    private Set<Usuario> usuarios;
}
