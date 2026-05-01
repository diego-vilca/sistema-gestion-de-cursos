package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.vicv.gestion_cursos.common.enums.EstadoUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;   
    private String password;
    private LocalDateTime fecha_registro;
    private EstadoUsuario estado;
    private Set<Inscripcion> inscripciones;
    private Set<Rol> roles;
    private Set<Resena> resenas;
}
