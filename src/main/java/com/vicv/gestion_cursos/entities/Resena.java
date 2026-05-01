package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Resena {
    private Long id;
    private int puntuacion;
    private String comentario;
    private LocalDateTime fecha_resena;
    private Usuario estudiante;
    private Curso curso;
}
