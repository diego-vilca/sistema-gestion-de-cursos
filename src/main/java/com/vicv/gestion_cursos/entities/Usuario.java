package com.vicv.gestion_cursos.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.vicv.gestion_cursos.common.enums.EstadoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@EqualsAndHashCode( onlyExplicitlyIncluded = true)                              // mejor que @EqualsAndHashCode(of = "id"), necesario para el uso Sets
@ToString( exclude = { "cursos", "inscripciones", "resenias", "roles"})         // excluyo colecciones en relaciones bidireccionales para evitar loop ciclico.
@Table(
    name = "usuarios",
    uniqueConstraints = {
        @UniqueConstraint( name = "UK_USUARIO_EMAIL", columnNames = "email")
    } 
)
public class Usuario {

    @Id
    @SequenceGenerator(
        name = "usuario_sequence",
        sequenceName = "usuario_sequence",
        allocationSize = 1
    )
    @GeneratedValue( 
        strategy = GenerationType.SEQUENCE,
        generator = "usuario_sequence"
    )
    @Column( name = "usuario_id", updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column( name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column( name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column( name = "email", nullable = false, length = 255)
    private String email;

    @Column( name = "password", nullable = false, length = 255)
    private String password;

    @Column( name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column( name = "estado_usuario", nullable = false, length = 50)
    private EstadoUsuario estado;

    // relaciones
    @OneToMany( 
        mappedBy = "instructor",
        cascade = CascadeType.PERSIST
    )
    private Set<Curso> cursos;

    @OneToMany( 
        mappedBy = "estudiante",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private Set<Inscripcion> inscripciones;

    @OneToMany( 
        mappedBy = "studiante",
        cascade = { CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private Set<Resenia> resenias;

    @ManyToMany( mappedBy = "usuarios")
    private Set<Rol> roles;

    

    // onstructores
    public Usuario() {
        this.cursos = new HashSet<>();
        this.inscripciones = new HashSet<>();
        this.resenias = new HashSet<>();
        this.roles = new HashSet<>();
    }



    public Usuario(Long id, String nombre, String apellido, String email, String password, LocalDateTime fechaRegistro,
            EstadoUsuario estado, Set<Curso> cursos, Set<Inscripcion> inscripciones, Set<Resenia> resenias,
            Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.cursos = cursos;
        this.inscripciones = inscripciones;
        this.resenias = resenias;
        this.roles = roles;
    }
    
}
