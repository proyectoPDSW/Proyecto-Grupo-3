/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.EquipoSencillo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author German Lopez
 */
public interface EquipoSencilloMapper {

    /**
     * Registra un equipo sencillo en la base de datos
     *
     * @param es
     */
    public void insertEquipo(@Param("EqSencillo") EquipoSencillo es);

    /**
     * Consulta un equipo sencillo con un nombre especifico
     *
     * @param nombre
     * @return el equipo sencillo que posee ese nombre
     */
    public EquipoSencillo loadEquipoByNombre(@Param("Eq_Nombre") String nombre);

    /**
     * Reemplazar un equipo sencillo "viejo" por uno que presente novedades
     *
     * @param eqv
     * @param eqn
     */
    public void update(@Param("Eq_Viejo") EquipoSencillo eqv, @Param("Eq_Nuevo") EquipoSencillo eqn);

    /**
     * Consulta todos los equipos sencillos que esten registrados en la base de
     * datos
     *
     * @return un arreglo con todos los equipos sencillos
     */
    public ArrayList<EquipoSencillo> loadAll();

    /**
     * Consulta los equipos sencillos con cantidad total mator a 0
     *
     * @param nombre
     * @return un numero que es la cantidad disponible
     */
    public EquipoSencillo loadEquipoByNombreDisponibilidad(@Param("Eq_Nombre") String nombre);

    /**
     * Consulta la cantidad disponible que tiene un equipo sencillo para hacer
     * prestamos
     *
     * @param nombre
     * @return
     */
    public int consultarEquipoSencilloCantidadDisponible(@Param("Eq_Nombre") String nombre);
        
    /**
     * Consulta los nombres de herramientas que contengan una cadena
     * 
     * @param toSearch la cadena a buscar
     * @return una lista que contiene los nombres que contienen una cadena
     */
    public List<String> loadHerramientaAproximada(@Param("Aproximado") String toSearch);
}
