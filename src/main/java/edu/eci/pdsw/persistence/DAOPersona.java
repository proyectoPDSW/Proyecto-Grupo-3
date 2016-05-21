/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Rol;
import java.util.List;

/**
 *
 * @author Hugo Alvarez
 */
public interface DAOPersona {

    /**
     * Obj: carga la persona con el carne dado. pre: el carne que debe ser una
     * cadena pos: devuelve la persona si la encuentra, si no devuelve null
     *
     * @param carne, es la cadena que dientifica a una persona
     * @return La persona que fue encontrada o no
     * @throws PersistenceException, bota la excepcion si no encuentra la
     * persona.
     */
    public abstract Persona load(String carne) throws PersistenceException;

    /**
     * Obj: Guarda la persona que es ingresada. pre: la persona a guardar
     *
     * @param persona, la persona a guardar.
     * @throws PersistenceException Si hubo un error al intentar guardar
     */
    public abstract void save(Persona persona) throws PersistenceException;

    /**
     * Obj: Registrar los roles de una persona pre: El carne de la persona y su
     * rol
     *
     * @param carne
     * @param rol
     * @throws PersistenceException
     */
    public abstract void save(String carne, Rol rol) throws PersistenceException;

    /**
     * Obj:Ingresar un rol pre: un nombre de un rol
     *
     * @param rol
     * @throws PersistenceException
     */
    public abstract void save(String rol) throws PersistenceException;

    /**
     * Obj: Obtener todos los roles de una persona pre: El carne de la persona
     * pos: Los roles que posee esa persona
     *
     * @param carne
     * @return
     * @throws PersistenceException
     */
    public abstract List<Rol> loadRoles(String carne) throws PersistenceException;

    /**
     * Obj: actualiza la persona que es ingresada. pre: la persona a actualizar
     *
     * @param persona, la persona a actualizar.
     * @throws PersistenceException Si hubo un error al actualizar, como
     * atributos vacios.
     */
    public abstract void update(Persona persona) throws PersistenceException;

    /**
     * Obj: Obtener todas las personas registradas. pos: obtener una lista de
     * personas que estan registradas en la base de datos
     *
     * @return la lista de personas registradas
     * @throws PersistenceException Si Hubo un error al cargar todas las
     * personas.
     */
    public abstract List<Persona> loadAll() throws PersistenceException;

    /**
     * Obj: Mostrar todos los roles que estan en la base de datos pos: Una lista
     * con todos los roles registrados
     *
     * @return
     * @throws PersistenceException
     */
    public abstract List<Rol> loadAllRoles() throws PersistenceException;

    public Persona loadPersRoles(String carne) throws PersistenceException;
    
    /**
     * Obj: Calcula la cantidad de prestamos en mora que ha tenido una persona
     * 
     * @param per el carnet de la persona de la que se quieren consultar las moras
     * @return la cantidad de prestamos en mora que ha tenido una persona
     * @throws PersistenceException 
     */
    public int calcMorasPrev(String per) throws PersistenceException;

}
