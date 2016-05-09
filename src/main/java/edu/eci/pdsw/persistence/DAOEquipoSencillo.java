/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

import edu.eci.pdsw.entities.EquipoSencillo;
import java.util.ArrayList;

/**
 *
 * @author David Useche
 */
public interface DAOEquipoSencillo {

    /**
     *Carga un equipo sencillo de la base de datos
     * @param nombre del equipo sencillo a cargar
     * @return el Equipo sencillo cargado
     * @throws PersistenceException si el equipo no existe
     */
    public EquipoSencillo load(String nombre) throws PersistenceException;

    /**
     * Guarda un equipo sencillo en la base de datos
     * @param toSave el Equipo sencillo a guardar
     * @throws PersistenceException si el Equipo sencillo ya existe
     */
    public void save(EquipoSencillo toSave) throws PersistenceException;

    /**
     * Actualiza un equipo sencillo
     * @param toUpdate el equipo sencillo actualizado
     * @throws PersistenceException si el equipo sencillo no existe
     */
    public void update(EquipoSencillo toUpdate) throws PersistenceException;

    /**
     * Carga todos los equipos sencillos de la base de datos
     * @return Una lista con los equipos sencillos 
     * @throws PersistenceException Si hay un error SQL
     */
    public ArrayList<EquipoSencillo> loadAll() throws PersistenceException;

    /**
     * Carga la cantidad de elementos de un equipo sencillo disponibles para prestar
     * @param nombre del Equipo sencillo a buscar
     * @return el numero de elemenetos que se pueden prestar
     * @throws PersistenceException si el Equipo sencillo no existe
     */
    public EquipoSencillo loadByNombreDisponibles(String nombre) throws PersistenceException;
    
    /**
     * Consulta la cantidad disponible de un equipo sencillo
     * para realizar prestamos
     * @param nomre
     * @return
     * @throws PersistenceException 
     */
    public int loadCantidadDisponibleEquipoSencillo(String nomre) throws PersistenceException;
}
