-- SEQUENCES

CREATE SEQUENCE categoria_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE curso_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE inscripcion_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE resenia_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE rol_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE usuario_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;







-- TABLES

CREATE TABLE categorias (
    categoria_id bigint NOT NULL DEFAULT nextval('categoria_sequence'),
    descripcion character varying(255),
    nombre character varying(255) NOT NULL
);



CREATE TABLE curso_categoria (
    categoria_id bigint NOT NULL,
    curso_id bigint NOT NULL
);


CREATE TABLE cursos (
    curso_id bigint NOT NULL DEFAULT nextval('curso_sequence'),
    fecha_creacion timestamp(6) without time zone NOT NULL,
    instructor_id bigint NOT NULL,
    titulo character varying(100) NOT NULL,
    descripcion text NOT NULL,
    dificultad character varying(255) NOT NULL,
    estado_curso character varying(255) NOT NULL,
    CONSTRAINT cursos_dificultad_check CHECK (((dificultad)::text = ANY ((ARRAY['BASICO'::character varying, 'INTERMEDIO'::character varying, 'AVANZADO'::character varying])::text[]))),
    CONSTRAINT cursos_estado_curso_check CHECK (((estado_curso)::text = ANY ((ARRAY['PUBLICADO'::character varying, 'BORRADOR'::character varying, 'ARCHIVADO'::character varying])::text[])))
);


CREATE TABLE inscripciones (
    inscripcion_id bigint NOT NULL DEFAULT nextval('inscripcion_sequence'),
    progreso integer NOT NULL,
    curso_id bigint NOT NULL,
    estudiante_id bigint NOT NULL,
    fecha_inscripcion timestamp(6) without time zone NOT NULL,
    estado_inscripcion character varying(255) NOT NULL,
    CONSTRAINT inscripciones_estado_inscripcion_check CHECK (((estado_inscripcion)::text = ANY ((ARRAY['EN_CURSO'::character varying, 'COMPLETADO'::character varying, 'ABANDONADO'::character varying])::text[])))
);



CREATE TABLE resenias (
    resenia_id bigint NOT NULL DEFAULT nextval('resenia_sequence'),
    puntuacion integer NOT NULL,
    curso_id bigint NOT NULL,
    estudiante_id bigint NOT NULL,
    fecha_resenia timestamp(6) without time zone NOT NULL,
    comentario text NOT NULL
);



CREATE TABLE roles (
    rol_id bigint NOT NULL DEFAULT nextval('rol_sequence'),
    nombre character varying(255) NOT NULL
);



CREATE TABLE usuario_rol (
    rol_id bigint NOT NULL,
    usuario_id bigint NOT NULL
);



CREATE TABLE usuarios (
    usuario_id bigint NOT NULL DEFAULT nextval('usuario_sequence'),
    fecha_registro timestamp(6) without time zone NOT NULL,
    estado_usuario character varying(50) NOT NULL,
    apellido character varying(100) NOT NULL,
    nombre character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT usuarios_estado_usuario_check CHECK (((estado_usuario)::text = ANY ((ARRAY['ACTIVO'::character varying, 'SUSPENDIDO'::character varying, 'ELIMINADO'::character varying])::text[])))
);






--  PRIMARY KEYS

ALTER TABLE  categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (categoria_id);


ALTER TABLE curso_categoria
    ADD CONSTRAINT curso_categoria_pkey PRIMARY KEY (categoria_id, curso_id);


ALTER TABLE cursos
    ADD CONSTRAINT cursos_pkey PRIMARY KEY (curso_id);


ALTER TABLE inscripciones
    ADD CONSTRAINT inscripciones_pkey PRIMARY KEY (inscripcion_id);


ALTER TABLE resenias
    ADD CONSTRAINT resenias_pkey PRIMARY KEY (resenia_id);


ALTER TABLE roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rol_id);


ALTER TABLE usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (rol_id, usuario_id);


ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (usuario_id);






-- UNIQUE

ALTER TABLE cursos
    ADD CONSTRAINT uk_curso_titulo UNIQUE (titulo);



ALTER TABLE inscripciones
    ADD CONSTRAINT uk_inscripciones_estudiante_curso UNIQUE (estudiante_id, curso_id);



ALTER TABLE resenias
    ADD CONSTRAINT uk_resenia_estudiante_curso UNIQUE (estudiante_id, curso_id);



ALTER TABLE usuarios
    ADD CONSTRAINT uk_usuario_email UNIQUE (email);







-- FOREIGN KEYS

ALTER TABLE curso_categoria
    ADD CONSTRAINT fk_curso_categoria_categoria_id__categorias FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id);


ALTER TABLE curso_categoria
    ADD CONSTRAINT fk_curso_categoria_curso_id__cursos FOREIGN KEY (curso_id) REFERENCES cursos(curso_id);



ALTER TABLE inscripciones
    ADD CONSTRAINT fk_inscripciones_curso_id__cursos FOREIGN KEY (curso_id) REFERENCES cursos(curso_id);



ALTER TABLE inscripciones
    ADD CONSTRAINT fk_inscripciones_estudiante_id__usuarios FOREIGN KEY (estudiante_id) REFERENCES usuarios(usuario_id);



ALTER TABLE resenias
    ADD CONSTRAINT fk_resenias_curso_id__cursos FOREIGN KEY (curso_id) REFERENCES cursos(curso_id);



ALTER TABLE resenias
    ADD CONSTRAINT fk_resenias_estudiante_id__usuarios FOREIGN KEY (estudiante_id) REFERENCES usuarios(usuario_id);



ALTER TABLE cursos
    ADD CONSTRAINT fk_cursos_instructor_id__usuarios FOREIGN KEY (instructor_id) REFERENCES usuarios(usuario_id);



ALTER TABLE usuario_rol
    ADD CONSTRAINT fk_usuario_rol_rol_id__roles FOREIGN KEY (rol_id) REFERENCES roles(rol_id);



ALTER TABLE usuario_rol
    ADD CONSTRAINT fk_usuario_rol_usuario_id__usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id);




-- INDICES

CREATE INDEX idx_inscripciones_curso_id ON inscripciones(curso_id);
CREATE INDEX idx_inscripciones_estudiante_id ON inscripciones(estudiante_id);

CREATE INDEX idx_resenias_curso_id ON resenias(curso_id);
CREATE INDEX idx_resenias_estudiante_id ON resenias(estudiante_id);

CREATE INDEX idx_cursos_instructor_id ON cursos(instructor_id);


