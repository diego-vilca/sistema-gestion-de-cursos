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
@Getter
@Setter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@ToString( exclude = "usuarios")
@Table(
    name = "roles"
)
public class Rol {
    @Id
    @SequenceGenerator(
        name = "rol_sequence",
        sequenceName = "rol_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rol_sequence"
    )
    @Column( name = "rol_id", updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column( name = "nombre", nullable = false)
    private String nombre;

    // relaciones
    @ManyToMany
    @JoinTable(                                                                      // creo la tabla intermedia y defino las FK
        name = "usuario_rol",
        joinColumns = @JoinColumn(
            name = "rol_id", 
            nullable = false,
            foreignKey = @ForeignKey( name = "FK_ROL")
        ),
        inverseJoinColumns = @JoinColumn( 
            name = "usuario_id", 
            nullable = false,
            foreignKey = @ForeignKey( name = "FK_USUARIO")
        )
    )
    private Set<Usuario> usuarios;


    // constructores
    public Rol() {
        this.usuarios = new HashSet<>();
    }

    public Rol(Long id, String nombre, Set<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    
}
