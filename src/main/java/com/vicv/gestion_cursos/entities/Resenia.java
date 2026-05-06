package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@Table( 
    name = "resenias",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_resenia_estudiante_curso",
        columnNames = {"estudiante_id", "curso_id"}
    )
)
public class Resenia {
    @Id
    @SequenceGenerator(
        name = "resenia_sequence",
        sequenceName = "resenia_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "resenia_sequence"
    )
    @Column( name = "resenia_id", updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column( name = "puntuacion", nullable = false)
    private int puntuacion;
    
    @Column( name = "comentario", nullable = false, columnDefinition = "TEXT")
    private String comentario;

    @Column( name = "fecha_resenia", nullable = false, updatable = false)
    private LocalDateTime fechaResenia;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
        name = "estudiante_id",
        nullable = false,
        foreignKey = @ForeignKey( name = "fk_resenias_estudiante_id__usuarios")
    )
    private Usuario estudiante;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(
        name = "curso_id",
        nullable = false,
        foreignKey = @ForeignKey( name = "fk_resenias_curso_id__cursos")
    )
    private Curso curso;
}
