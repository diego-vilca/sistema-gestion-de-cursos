package com.vicv.gestion_cursos.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@ToString( exclude = { "cursos"})
@Table( name = "categorias")
public class Categoria {
    @Id
    @SequenceGenerator(
        name = "categoria_sequence",
        sequenceName = "categoria_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "categoria_sequence"
    )
    @Column( name = "categoria_id", updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column( name = "nombre", nullable = false)
    private String nombre;

    @Column( name = "descripcion", nullable = true)
    private String descripcion;

    // relaciones
    @ManyToMany
    @JoinTable(                                                                     // creo la tabla intermedia y defino las FK
        name = "curso_categoria",
        joinColumns = @JoinColumn( 
            name = "categoria_id", 
            nullable = false,
            foreignKey = @ForeignKey( name = "fk_curso_categoria_categoria_id__categorias")
        ),
        inverseJoinColumns = @JoinColumn( 
            name = "curso_id", 
            nullable = false,
            foreignKey = @ForeignKey( name = "fk_curso_categoria_curso_id__cursos")
        )
    )
    private Set<Curso> cursos;



    // constructores
    public Categoria() {
        this.cursos = new HashSet<>();
    }

    public Categoria(Long id, String nombre, String descripcion, Set<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cursos = cursos;
    }

    

}
