-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-04-21 23:51:17.484

-- tables
-- Table: Departamento_persona
CREATE TABLE Departamento_persona (
    departamento varchar(50) NOT NULL,
    persona varchar(20) NOT NULL,
    CONSTRAINT Departamento_persona_pk PRIMARY KEY (departamento,persona)
)ENGINE=InnoDB;

-- Table: Departamentos
CREATE TABLE Departamentos (
    departamento varchar(50) NOT NULL,
    CONSTRAINT Departamentos_pk PRIMARY KEY (departamento)
)ENGINE=InnoDB;

-- Table: Equipo_prestamo_complejo
CREATE TABLE Equipo_prestamo_complejo (
    equipo_complejo int NOT NULL,
    prestamo int NOT NULL,
    devuelto bool NOT NULL,
    CONSTRAINT Equipo_prestamo_complejo_pk PRIMARY KEY (equipo_complejo,prestamo)
)ENGINE=InnoDB;

-- Table: Equipo_prestamo_sencillo
CREATE TABLE Equipo_prestamo_sencillo (
    prestamo int NOT NULL,
    equipo varchar(100) NOT NULL,
    cantidad int NOT NULL,
    cantidad_devuelta int NOT NULL,
    CONSTRAINT Equipo_prestamo_sencillo_pk PRIMARY KEY (prestamo,equipo)
)ENGINE=InnoDB;

-- Table: Equipos_Complejos
CREATE TABLE Equipos_Complejos (
    id_equipo_complejo int NOT NULL AUTO_INCREMENT,
    serial varchar(30) NULL,
    num_placa int NULL,
    disponibilidad bool NOT NULL,
    estado varchar(50) NOT NULL,
    modelo varchar(50) NOT NULL,
    asegurado bool NOT NULL,
    marca varchar(30) NOT NULL,
    UNIQUE INDEX SERIAL_AK (serial),
    UNIQUE INDEX NUM_PLACA_AK (num_placa),
    CONSTRAINT Equipos_Complejos_pk PRIMARY KEY (id_equipo_complejo)
)ENGINE=InnoDB;

-- Table: Equipos_Sencillos
CREATE TABLE Equipos_Sencillos (
    nombre varchar(100) NOT NULL,
    cantidad_total int NOT NULL,
    clase varchar(100) NOT NULL,
    fotografia varchar(500) NOT NULL,
    valor_comercial int NOT NULL,
    UNIQUE INDEX NOMBRE_AK (nombre),
    CONSTRAINT Equipos_Sencillos_pk PRIMARY KEY (nombre)
)ENGINE=InnoDB;

-- Table: Informacion_Compra
CREATE TABLE Informacion_Compra (
    id int NOT NULL AUTO_INCREMENT,
    equipo int NOT NULL,
    fecha_compra date NOT NULL,
    proveedor varchar(100) NOT NULL,
    fecha_garantia date NOT NULL,
    CONSTRAINT Informacion_Compra_pk PRIMARY KEY (id)
)ENGINE=InnoDB;

-- Table: Modelos
CREATE TABLE Modelos (
    vida_util int NOT NULL,
    fotografia varchar(500) NOT NULL,
    valor_comercial int NOT NULL,
    clase varchar(50) NOT NULL,
    descripcion varchar(200) NOT NULL,
    accesorios varchar(300) NOT NULL,
    nombre varchar(100) NOT NULL,
    CONSTRAINT Modelos_pk PRIMARY KEY (nombre)
)ENGINE=InnoDB;

-- Table: Personas
CREATE TABLE Personas (
    carne varchar(20) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    telefono varchar(20) NOT NULL,
    rol varchar(50) NOT NULL,
    UNIQUE INDEX EMAIL_AK (email),
    CHECK (email like '%.escuelaing.edu.co'),
    CHECK (rol in ('estudiante','profesor','laboratorista')),
    CONSTRAINT Personas_pk PRIMARY KEY (carne)
)ENGINE=InnoDB;

-- Table: Prestamos
CREATE TABLE Prestamos (
    id_prestamo int NOT NULL AUTO_INCREMENT,
    fecha_inicio timestamp NOT NULL,
    persona varchar(20) NOT NULL,
    fecha_fin_estimada timestamp NULL,
    fecha_fin_real timestamp NULL,
    tipo_prestamo varchar(100) NOT NULL,
    CONSTRAINT Prestamos_pk PRIMARY KEY (id_prestamo)
)ENGINE=InnoDB;

-- foreign keys
-- Reference: Departamento_persona_Departamentos (table: Departamento_persona)
ALTER TABLE Departamento_persona ADD CONSTRAINT Departamento_persona_Departamentos FOREIGN KEY (departamento)
    REFERENCES Departamentos (departamento);

-- Reference: Departamento_persona_Personas (table: Departamento_persona)
ALTER TABLE Departamento_persona ADD CONSTRAINT Departamento_persona_Personas FOREIGN KEY (persona)
    REFERENCES Personas (carne);

-- Reference: Equipo_prestamo_complejo_Equipos_Complejos (table: Equipo_prestamo_complejo)
ALTER TABLE Equipo_prestamo_complejo ADD CONSTRAINT Equipo_prestamo_complejo_Equipos_Complejos FOREIGN KEY (equipo_complejo)
    REFERENCES Equipos_Complejos (id_equipo_complejo);

-- Reference: Equipo_prestamo_complejo_Prestamos_complejos (table: Equipo_prestamo_complejo)
ALTER TABLE Equipo_prestamo_complejo ADD CONSTRAINT Equipo_prestamo_complejo_Prestamos_complejos FOREIGN KEY (prestamo)
    REFERENCES Prestamos (id_prestamo);

-- Reference: Equipo_prestamo_sencillo_Equipos_Sencillos (table: Equipo_prestamo_sencillo)
ALTER TABLE Equipo_prestamo_sencillo ADD CONSTRAINT Equipo_prestamo_sencillo_Equipos_Sencillos FOREIGN KEY (equipo)
    REFERENCES Equipos_Sencillos (nombre);

-- Reference: Equipo_prestamo_sencillo_Prestamos_complejos (table: Equipo_prestamo_sencillo)
ALTER TABLE Equipo_prestamo_sencillo ADD CONSTRAINT Equipo_prestamo_sencillo_Prestamos_complejos FOREIGN KEY (prestamo)
    REFERENCES Prestamos (id_prestamo);

-- Reference: Equipos_Complejos_Modelos (table: Equipos_Complejos)
ALTER TABLE Equipos_Complejos ADD CONSTRAINT Equipos_Complejos_Modelos FOREIGN KEY (modelo)
    REFERENCES Modelos (nombre);

-- Reference: Ordenes_compra_Equipos_Complejos (table: Informacion_Compra)
ALTER TABLE Informacion_Compra ADD CONSTRAINT Ordenes_compra_Equipos_Complejos FOREIGN KEY (equipo)
    REFERENCES Equipos_Complejos (id_equipo_complejo);

-- Reference: Persona_Prestamo (table: Prestamos)
ALTER TABLE Prestamos ADD CONSTRAINT Persona_Prestamo FOREIGN KEY (persona)
    REFERENCES Personas (carne);

-- End of file.
