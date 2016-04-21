-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-04-18 12:56:58.479

-- tables
-- Table: Departamento_persona
CREATE TABLE Departamento_persona (
    departamento varchar(50) NOT NULL,
    persona int NOT NULL,
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
    CONSTRAINT Equipo_prestamo_complejo_pk PRIMARY KEY (equipo_complejo,prestamo)
)ENGINE=InnoDB;

-- Table: Equipo_prestamo_sencillo
CREATE TABLE Equipo_prestamo_sencillo (
    prestamo int NOT NULL,
    equipo varchar(100) NOT NULL,
    cantidad int NOT NULL,
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
)
ENGINE=InnoDB;

-- Table: Equipos_Sencillos
CREATE TABLE Equipos_Sencillos (
    nombre varchar(100) NOT NULL,
    cantidad_total int NOT NULL,
    clase varchar(100) NOT NULL,
    fotografia blob NOT NULL,
    valor_comercial int NOT NULL,
    UNIQUE INDEX NOMBRE_AK (nombre),
    CONSTRAINT Equipos_Sencillos_pk PRIMARY KEY (nombre)
)
ENGINE=InnoDB;

-- Table: Modelos
CREATE TABLE Modelos (
    id_modelo varchar(50) NOT NULL,
    vida_util int NOT NULL,
    fotografia blob NOT NULL,
    valor_comercial int NOT NULL,
    clase varchar(50) NOT NULL,
    descripcion varchar(200) NOT NULL,
    accesorios varchar(300) NOT NULL,
    nombre varchar(100) NOT NULL,
    CONSTRAINT Modelos_pk PRIMARY KEY (id_modelo)
)ENGINE=InnoDB;

-- Table: Ordenes_compra
CREATE TABLE Ordenes_compra (
    id int NOT NULL AUTO_INCREMENT,
    equipo int NOT NULL,
    fecha_compra date NOT NULL,
    proveedor varchar(100) NOT NULL,
    fecha_garantia date NOT NULL,
    CONSTRAINT Ordenes_compra_pk PRIMARY KEY (id)
)ENGINE=InnoDB;

-- Table: Personas
CREATE TABLE Personas (
    carne int NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    telefono varchar(20) NOT NULL,
    UNIQUE INDEX EMAIL_AK (email),
    CHECK (email like '%.escuelaing.edu.co'),
    
    CONSTRAINT Personas_pk PRIMARY KEY (carne)
)ENGINE=InnoDB;

-- Table: Prestamos
CREATE TABLE Prestamos (
    id_prestamo int NOT NULL AUTO_INCREMENT,
    fecha_inicio date NOT NULL,
    persona int NOT NULL,
    fecha_fin_estimada date NULL,
    fecha_fin_real date NULL,
    CONSTRAINT Prestamos_pk PRIMARY KEY (id_prestamo)
)ENGINE=InnoDB;

-- Table: Rol_persona
CREATE TABLE Rol_persona (
    persona int NOT NULL,
    rol varchar(50) NOT NULL,
    CHECK (rol in ('estudiante','profesor','laboratorista')),
    CONSTRAINT Rol_persona_pk PRIMARY KEY (persona,rol)
)ENGINE=InnoDB;

-- Table: Roles
CREATE TABLE Roles (
    rol varchar(50) NOT NULL,
    CONSTRAINT Roles_pk PRIMARY KEY (rol)
)ENGINE=InnoDB;

-- foreign keys
-- Reference: Departamento_persona_Departamentos (table: Departamento_persona)
ALTER TABLE Departamento_persona ADD CONSTRAINT Departa_persona_Departa FOREIGN KEY(departamento) REFERENCES Departamentos(departamento);

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
    REFERENCES Modelos (id_modelo);

-- Reference: Ordenes_compra_Equipos_Complejos (table: Ordenes_compra)
ALTER TABLE Ordenes_compra ADD CONSTRAINT Ordenes_compra_Equipos_Complejos FOREIGN KEY (equipo)
    REFERENCES Equipos_Complejos (id_equipo_complejo);

-- Reference: Persona_Prestamo (table: Prestamos)
ALTER TABLE Prestamos ADD CONSTRAINT Persona_Prestamo FOREIGN KEY (persona)
    REFERENCES Personas (carne);

-- Reference: Table_17_Personas (table: Rol_persona)
ALTER TABLE Rol_persona ADD CONSTRAINT Table_17_Personas FOREIGN KEY(persona)
    REFERENCES Personas (carne);

-- Reference: Table_17_Roles (table: Rol_persona)
ALTER TABLE Rol_persona ADD CONSTRAINT Table_17_Roles FOREIGN KEY (rol)
    REFERENCES Roles (rol);

-- End of file.
