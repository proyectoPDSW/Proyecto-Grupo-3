-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-05-07 14:58:36.154

-- foreign keys
ALTER TABLE Departamento_persona
    DROP FOREIGN KEY Departamento_persona_Departamentos;

ALTER TABLE Departamento_persona
    DROP FOREIGN KEY Departamento_persona_Personas;

ALTER TABLE Equipo_prestamo_complejo
    DROP FOREIGN KEY Equipo_prestamo_complejo_Equipos_Complejos;

ALTER TABLE Equipo_prestamo_complejo
    DROP FOREIGN KEY Equipo_prestamo_complejo_Prestamos;

ALTER TABLE Equipo_prestamo_sencillo
    DROP FOREIGN KEY Equipo_prestamo_sencillo_Equipos_Sencillos;

ALTER TABLE Equipo_prestamo_sencillo
    DROP FOREIGN KEY Equipo_prestamo_sencillo_Prestamos;

ALTER TABLE Equipos_Complejos
    DROP FOREIGN KEY Equipos_Complejos_Modelos;

ALTER TABLE Informacion_Compra
    DROP FOREIGN KEY Informacion_Compra_Equipos_Complejos;

ALTER TABLE Prestamos
    DROP FOREIGN KEY Persona_Prestamo;

ALTER TABLE Rol_Persona
    DROP FOREIGN KEY Rol_Persona_Personas;

ALTER TABLE Rol_Persona
    DROP FOREIGN KEY Rol_Persona_Rol;

-- tables
DROP TABLE Departamento_persona;

DROP TABLE Departamentos;

DROP TABLE Equipo_prestamo_complejo;

DROP TABLE Equipo_prestamo_sencillo;

DROP TABLE Equipos_Complejos;

DROP TABLE Equipos_Sencillos;

DROP TABLE Informacion_Compra;

DROP TABLE Modelos;

DROP TABLE Personas;

DROP TABLE Prestamos;

DROP TABLE Rol;

DROP TABLE Rol_Persona;

-- End of file.

