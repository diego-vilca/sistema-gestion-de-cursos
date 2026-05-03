package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.vicv.gestion_cursos.common.enums.EstadoCurso;
import com.vicv.gestion_cursos.common.enums.NivelCurso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@ToString( exclude = { "instructor", "inscripciones", "resenias", "categorias"})
@Table(
    name = "cursos",
    uniqueConstraints = @UniqueConstraint( name = "UK_CURSO_TITULO", columnNames = "titulo") 
)
public class Curso {

    @Id
    @SequenceGenerator(
        name = "curso_sequence",
        sequenceName = "curso_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "curso_sequence"
    )
    @Column( name = "curso_id", updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column( name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column( name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column( name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated( EnumType.STRING)
    @Column( name = "estado_curso", nullable = false)
    private EstadoCurso estado;

    @Enumerated( EnumType.STRING)
    @Column( name = "dificultad", nullable = false)
    private NivelCurso nivel;

    // relaciones
    @ManyToOne
    @JoinColumn(
        name = "instructor_id",
        nullable = false,
        foreignKey = @ForeignKey( name = "FK_INSTRUCTOR")
    )  
    private Usuario instructor;

    @OneToMany(
        mappedBy = "curso"      // referencia al atributo 'curso' de la clase Inscripcion
    )
    private Set<Inscripcion> inscripciones;

    @OneToMany(
        mappedBy = "curso"
    )
    private Set<Resenia> resenias;

    @ManyToMany( mappedBy = "cursos")
    private Set<Categoria> categorias;

    
    // constructores
    public Curso() {
        this.inscripciones = new HashSet<>();
        this.resenias = new HashSet<>();
        this.categorias = new HashSet<>();
    }



    public Curso(Long id, String titulo, String descripcion, LocalDateTime fechaCreacion, EstadoCurso estado,
            NivelCurso nivel, Usuario instructor, Set<Inscripcion> inscripciones, Set<Resenia> resenias,
            Set<Categoria> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.nivel = nivel;
        this.instructor = instructor;
        this.inscripciones = inscripciones;
        this.resenias = resenias;
        this.categorias = categorias;
    }

    
    

}
