package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;

import com.vicv.gestion_cursos.common.enums.EstadoInscripcion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Inscripcion {
    private Long id;
    private LocalDateTime fecha_inscripcion;
    private int progreso;
    private EstadoInscripcion estado;
    private Usuario estudiante;
    private Curso curso;
}
