package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;

import com.vicv.gestion_cursos.common.enums.EstadoInscripcion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@ToString( exclude = { "estudiante", "curso"})
@Table(
    name = "inscripciones",
    uniqueConstraints = @UniqueConstraint(                  // define restricciones de unicidad.
        name = "UK_INSCRIPCIONES_ESTUDIANTE_CURSO",   // unique key constraint, la combinación de las columnas estudiante_id
        columnNames = {"estudiante_id", "curso_id"}         // y curso_id debe ser única
    )
)
public class Inscripcion {
    @Id
    @SequenceGenerator(
        name = "inscripcion_sequence",          // nombre del generador
        sequenceName = "inscripcion_sequence",  // nombre de la sequence en la DB
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "inscripcion_sequence"      // utilizo nombre del generador
    )
    @Column( name = "inscripcion_id", nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column( name = "fecha_inscripcion", nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;
    
    @Column( name = "progreso", nullable = false)
    private int progreso;

    @Enumerated(EnumType.STRING)
    @Column( name = "estado_inscripcion", nullable = false)
    private EstadoInscripcion estado;

    // relaciones
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
        name = "estudiante_id",     
        nullable = false,
        foreignKey = @ForeignKey( name = "FK_ESTUDIANTE")
    )
    private Usuario estudiante;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
        name = "curso_id",                                  // nombre de la columna de la FK
        nullable = false,           
        foreignKey = @ForeignKey( name = "FK_CURSO")        // nombre de la FK, util para debug, sino hibernate implementaria un nombre random
    )
    private Curso curso;
}
