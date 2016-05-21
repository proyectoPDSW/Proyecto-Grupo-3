-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-05-04 18:18:59.185

-- tables
-- Table: Departamento_persona
CREATE TABLE Departamento_persona (
    departamento varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    persona varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Departamento_persona_pk PRIMARY KEY (departamento,persona)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Departamentos
CREATE TABLE Departamentos (
    departamento varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Departamentos_pk PRIMARY KEY (departamento)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Equipo_prestamo_complejo
CREATE TABLE Equipo_prestamo_complejo (
    Prestamos_fecha_inicio timestamp NOT NULL,
    Prestamos_persona varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    Equipos_Complejos_serial varchar(30) COLLATE utf8_unicode_ci NOT NULL,
    Equipos_Complejos_modelo varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    estado varchar(50) NOT NULL,
    CONSTRAINT Equipo_prestamo_complejo_pk PRIMARY KEY (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo)
) ENGINE=InnoDB COLLATE utf8_general_ci;

-- Table: Equipo_prestamo_sencillo
CREATE TABLE Equipo_prestamo_sencillo (
    equipo varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    cantidad int NOT NULL,
    cantidad_devuelta int NOT NULL,
    Prestamos_fecha_inicio timestamp NOT NULL,
    Prestamos_persona varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Equipo_prestamo_sencillo_pk PRIMARY KEY (equipo,Prestamos_fecha_inicio,Prestamos_persona)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Equipos_Complejos
CREATE TABLE Equipos_Complejos (
    serial varchar(30) COLLATE utf8_unicode_ci NOT NULL,
    num_placa varchar(50) NOT NULL,
    disponibilidad bool NOT NULL,
    estado varchar(50) NOT NULL,
    modelo varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    asegurado bool NOT NULL,
    vida_restante int NOT NULL,
    UNIQUE INDEX Equipos_Complejos_ak_1 (num_placa),
    CONSTRAINT Equipos_Complejos_pk PRIMARY KEY (serial,modelo)
) ENGINE=InnoDB COLLATE utf8_general_ci;

-- Table: Equipos_Sencillos
CREATE TABLE Equipos_Sencillos (
    nombre varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    cantidad_total int NOT NULL,
    clase varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    fotografia varchar(500) COLLATE utf8_unicode_ci NULL,
    valor_comercial int NOT NULL,
    UNIQUE INDEX NOMBRE_AK (nombre),
    CONSTRAINT Equipos_Sencillos_pk PRIMARY KEY (nombre)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Informacion_Compra
CREATE TABLE Informacion_Compra (
    fecha_compra timestamp NOT NULL,
    proveedor varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    fecha_garantia timestamp NOT NULL,
    Equipos_Complejos_serial varchar(30) COLLATE utf8_unicode_ci NOT NULL,
    Equipos_Complejos_modelo varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    codigo_orden_compra varchar(100) NOT NULL,
    codigo_activo varchar(100) NOT NULL,
    CONSTRAINT Informacion_Compra_pk PRIMARY KEY (Equipos_Complejos_serial,Equipos_Complejos_modelo)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Modelos
CREATE TABLE Modelos (
    vida_util int NOT NULL,
    fotografia varchar(500) COLLATE utf8_unicode_ci NULL,
    valor_comercial int NOT NULL,
    clase varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    descripcion varchar(200) COLLATE utf8_unicode_ci NOT NULL,
    accesorios varchar(300) COLLATE utf8_unicode_ci NOT NULL,
    nombre varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    marca varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Modelos_pk PRIMARY KEY (nombre)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Personas
CREATE TABLE Personas (
    carne varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    nombre varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    apellido varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    email varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    telefono varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    UNIQUE INDEX EMAIL_AK (email),
    CHECK (email like '%.escuelaing.edu.co'),
    CONSTRAINT Personas_pk PRIMARY KEY (carne)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Prestamos
CREATE TABLE Prestamos (
    fecha_inicio timestamp  NOT NULL,
    persona varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    fecha_fin_estimada timestamp NULL,
    fecha_fin_real timestamp NULL,
    tipo_prestamo varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Prestamos_pk PRIMARY KEY (fecha_inicio,persona)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Rol
CREATE TABLE Rol (
    rol varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Rol_pk PRIMARY KEY (rol)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- Table: Rol_Persona
CREATE TABLE Rol_Persona (
    Personas_carne varchar(20) COLLATE utf8_unicode_ci NOT NULL,
    Rol_rol varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    contrasena varchar(200) COLLATE utf8_unicode_ci NOT NULL,
    sal varchar(100) COLLATE utf8_unicode_ci NOT NULL,
    CONSTRAINT Rol_Persona_pk PRIMARY KEY (Personas_carne,Rol_rol)
)ENGINE=InnoDB COLLATE utf8_unicode_ci;

-- foreign keys
-- Reference: Departamento_persona_Departamentos (table: Departamento_persona)
ALTER TABLE Departamento_persona ADD CONSTRAINT Departamento_persona_Departamentos FOREIGN KEY (departamento)
    REFERENCES Departamentos (departamento);

-- Reference: Departamento_persona_Personas (table: Departamento_persona)
ALTER TABLE Departamento_persona ADD CONSTRAINT Departamento_persona_Personas FOREIGN KEY (persona)
    REFERENCES Personas (carne);

-- Reference: Equipo_prestamo_complejo_Equipos_Complejos (table: Equipo_prestamo_complejo)
ALTER TABLE Equipo_prestamo_complejo ADD CONSTRAINT Equipo_prestamo_complejo_Equipos_Complejos FOREIGN KEY (Equipos_Complejos_serial,Equipos_Complejos_modelo)
    REFERENCES Equipos_Complejos (serial,modelo);
-- no funciona esta sentencia
-- Reference: Equipo_prestamo_complejo_Prestamos (table: Equipo_prestamo_complejo)
ALTER TABLE Equipo_prestamo_complejo ADD CONSTRAINT Equipo_prestamo_complejo_Prestamos FOREIGN KEY (Prestamos_fecha_inicio,Prestamos_persona)
    REFERENCES Prestamos (fecha_inicio,persona);

-- Reference: Equipo_prestamo_sencillo_Equipos_Sencillos (table: Equipo_prestamo_sencillo)
ALTER TABLE Equipo_prestamo_sencillo ADD CONSTRAINT Equipo_prestamo_sencillo_Equipos_Sencillos FOREIGN KEY (equipo)
    REFERENCES Equipos_Sencillos (nombre);

-- Reference: Equipo_prestamo_sencillo_Prestamos (table: Equipo_prestamo_sencillo)
ALTER TABLE Equipo_prestamo_sencillo ADD CONSTRAINT Equipo_prestamo_sencillo_Prestamos FOREIGN KEY (Prestamos_fecha_inicio,Prestamos_persona)
    REFERENCES Prestamos (fecha_inicio,persona);

-- Reference: Equipos_Complejos_Modelos (table: Equipos_Complejos)
ALTER TABLE Equipos_Complejos ADD CONSTRAINT Equipos_Complejos_Modelos FOREIGN KEY (modelo)
    REFERENCES Modelos (nombre);

-- Reference: Informacion_Compra_Equipos_Complejos (table: Informacion_Compra)
ALTER TABLE Informacion_Compra ADD CONSTRAINT Informacion_Compra_Equipos_Complejos FOREIGN KEY (Equipos_Complejos_serial,Equipos_Complejos_modelo)
    REFERENCES Equipos_Complejos (serial,modelo);

-- Reference: Persona_Prestamo (table: Prestamos)
ALTER TABLE Prestamos ADD CONSTRAINT Persona_Prestamo FOREIGN KEY (persona)
    REFERENCES Personas (carne);

-- Reference: Rol_Persona_Personas (table: Rol_Persona)
ALTER TABLE Rol_Persona ADD CONSTRAINT Rol_Persona_Personas FOREIGN KEY (Personas_carne)
    REFERENCES Personas (carne);

-- Reference: Rol_Persona_Rol (table: Rol_Persona)
ALTER TABLE Rol_Persona ADD CONSTRAINT Rol_Persona_Rol FOREIGN KEY (Rol_rol)
    REFERENCES Rol (rol);
alter table Equipo_prestamo_sencillo change Prestamos_fecha_inicio Prestamos_fecha_inicio timestamp not null default 0;
alter table Prestamos change fecha_inicio fecha_inicio timestamp not null default 0;
alter table Equipo_prestamo_complejo change Prestamos_fecha_inicio Prestamos_fecha_inicio timestamp not null default 0;
alter table Informacion_Compra change fecha_compra fecha_compra timestamp not null default 0;
alter table Informacion_Compra change fecha_garantia fecha_garantia timestamp not null default 0;
-- End of file.
