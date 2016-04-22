-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-04-21 23:51:17.484

-- foreign keys
ALTER TABLE Departamento_persona
    DROP FOREIGN KEY Departamento_persona_Departamentos;

ALTER TABLE Departamento_persona
    DROP FOREIGN KEY Departamento_persona_Personas;

ALTER TABLE Equipo_prestamo_complejo
    DROP FOREIGN KEY Equipo_prestamo_complejo_Equipos_Complejos;

ALTER TABLE Equipo_prestamo_complejo
    DROP FOREIGN KEY Equipo_prestamo_complejo_Prestamos_complejos;

ALTER TABLE Equipo_prestamo_sencillo
    DROP FOREIGN KEY Equipo_prestamo_sencillo_Equipos_Sencillos;

ALTER TABLE Equipo_prestamo_sencillo
    DROP FOREIGN KEY Equipo_prestamo_sencillo_Prestamos_complejos;

ALTER TABLE Equipos_Complejos
    DROP FOREIGN KEY Equipos_Complejos_Modelos;

ALTER TABLE Informacion_Compra
    DROP FOREIGN KEY Ordenes_compra_Equipos_Complejos;

ALTER TABLE Prestamos
    DROP FOREIGN KEY Persona_Prestamo;

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

-- End of file.
