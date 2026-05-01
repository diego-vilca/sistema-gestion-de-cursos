package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.vicv.gestion_cursos.common.enums.EstadoCurso;
import com.vicv.gestion_cursos.common.enums.NivelCurso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Curso {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_creacion;
    private EstadoCurso estado;
    private NivelCurso nivel;
    private Usuario instructor;
    private Set<Inscripcion> inscripciones;
    private Set<Resena> resenas;
    private Set<Categoria> categorias;
}
