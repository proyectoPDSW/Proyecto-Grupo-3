/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

import java.util.ArrayList;
import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import java.util.Date;
import java.util.List;

/**
 *
 * @author David Useche
 */
public interface DAOEquipoComplejo {

    /**
     * Carga un equipo complejo de la base de datos por serial
     *
     * @param serial a buscar
     * @param modelo del equipo a buscar
     * @return El equipo complejo cargado
     * @throws PersistenceException si no encontro el serial para ese modelo o
     * el modelo no existe
     */
    public EquipoComplejo load(String modelo, String serial) throws PersistenceException;

    /**
     * Carga un equipo complejo de la base de datos por placa
     *
     * @param placa a buscar
     * @return El equipo complejo cargado
     * @throws PersistenceException si no encontro la placa para ese modelo o el
     * modelo no existe
     */
    public EquipoComplejo load(String placa) throws PersistenceException;

    /**
     * Guarda un equipo complejo en la base de datos
     *
     * @param toSave Equipo a guardar
     * @throws PersistenceException si el equipo ya esta registrado
     */
    public void save(EquipoComplejo toSave) throws PersistenceException;

    /**
     * Actualiza un equipo en la base de datos
     *
     * @param toUpdate el equipo actualizado
     * @throws PersistenceException si el equipo no existe
     */
    public void update(EquipoComplejo toUpdate) throws PersistenceException;

    /**
     * Carga todos los equipos complejos de la base de datos
     *
     * @return Una lista que contiene todos los equipos
     * @throws PersistenceException Si hay algun error SQL
     */
    public ArrayList<EquipoComplejo> loadAll() throws PersistenceException;

    /**
     * Carga todos los equipos en la base de datos que puedan prestarse
     *
     * @return Una lista con los equipos complejos disponibles
     * @throws PersistenceException Si hay algun error SQL
     */
    public ArrayList<EquipoComplejo> loadDisponibles() throws PersistenceException;

    /**
     * Obtiene todos los equipos de un modelo
     *
     * @param modelo usado para buscar
     * @return Una lista con los equipos que son del modelo
     * @throws PersistenceException
     */
    public ArrayList<EquipoComplejo> loadByModelo(String modelo) throws PersistenceException;

    /**
     * Obtiene todos los equipos de un modelo que se encuentren en el almacen
     *
     * @param modelo usado para buscar
     * @return Una lista con los equipos que son del modelo y se encuentren en
     * el almacen
     * @throws PersistenceException
     */
    public ArrayList<EquipoComplejo> loadEnAlmacenByModelo(String modelo) throws PersistenceException;

    /**
     * Guarda un modelo en la base de datos
     *
     * @param model a guardar
     * @throws PersistenceException si el modelo ya existe
     */
    public void saveModelo(Modelo model) throws PersistenceException;
    
    /**
     * Guarda la orden de compra del equipo complejo en la base de datos
     *
     * @param ordenCompra
     * @param serialEquipo
     * @param model
     * @throws PersistenceException si la orden de compra ya existe
     */
    
    public void saveOrdenCompra(OrdenCompra ordenCompra, String serialEquipo, Modelo model) throws PersistenceException;

    /**
     * Carga un modelo de la base de datos
     *
     * @param nombre del modelo a cargar
     * @return modelo cargado
     * @throws PersistenceException si el modelo no existe
     */
    public Modelo loadModelo(String nombre) throws PersistenceException;

    /**
     * Carga todos los modelos que contengan una cadena
     *
     * @param toSearch la cadena a buscar
     * @return una lista que contiene los modelos que contienen una cadena
     */
    public List<String> loadModelosAproximados(String toSearch);
    
    /**
     * Carga la orden de compra del equipo complejo por serial
     *
     * @param serial del equipo complejo
     * @param modelo
     * @return la orden de compra del equipo
     * @throws PersistenceException si no existe el equipo con el seria, si la orden de compra no existe
     */
    
    public OrdenCompra loadOrdenCompraBySerial(String serial, String modelo) throws PersistenceException;
    
    /**
     * Consulta un equipo complejo por placa y ademas que este en almacen
     * es decir que este disponible para prestar
     * @param placa
     * @return EquipoCompejo
     * @throws PersistenceException si el equipo con la placa no esta registrado, el equipo no esta disponible
     */
    public EquipoComplejo loadEnAlmacenPorPlaca(String placa) throws PersistenceException;
    
    /**
     * calcula la fecha actual
     * @return la fecha actual
     */
    public Date currDate();
}
