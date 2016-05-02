/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Modelo;
import java.util.List;

/**
 *
 * @author 2107803
 */
public abstract class ServiciosEquipoComplejo {

    private static ServiciosEquipoComplejo instance = new ServiciosEquipoComplejoPersistence();

    public static ServiciosEquipoComplejo getInstance() {
        return instance;
    }

    /**
     * Registra un modelo
     * @param model a registrar
     * @throws ExcepcionServicios si el modelo ya esta registrado
     */
    public abstract void registrarModelo(Modelo model)throws ExcepcionServicios;

    /**
     * Registra un equipo complejo
     * @param equipo a registrar
     * @throws ExcepcionServicios si el equipo ya esta registrado
     */
    public abstract void registrarEquipoComplejo(EquipoComplejo equipo)throws ExcepcionServicios;

    /**
     * Consulta todos los equipos registrados
     * @return Una lista con los equipos registrados
     * @throws ExcepcionServicios si no se pudo consultar
     */
    public abstract List<EquipoComplejo> consultarTodo()throws ExcepcionServicios;

    /**
     * Consulta todos los equipos que sean de cierto modelo
     * @param modelo del que se buscaran los equipos
     * @return Los equipos que son de cierto modelo
     * @throws ExcepcionServicios Si el modelo no existe
     */
    public abstract List<EquipoComplejo> consultarPorModelo(String modelo)throws ExcepcionServicios;

    /**
     * Consulta el equipo de placa numPlaca
     * @param numPlaca que posee el equipo a consultar
     * @param modelo del equipo a buscar
     * @return El equipo que posee esa placa
     * @throws ExcepcionServicios si la placa no esta registrada
     */
    public abstract EquipoComplejo consultarPorPlaca(String modelo,int numPlaca)throws ExcepcionServicios;

    /**
     * Consulta el equipo que tenga cierto serial
     * @param serial que posee el equipo a consultar
     * @param modelo del equipo a buscar
     * @return El equipo que contiene ese serial
     * @throws ExcepcionServicios Si el serial no esta registrado
     */
    public abstract EquipoComplejo consultarPorSerial(String modelo,String serial)throws ExcepcionServicios;

    /**
     * Consulta los equipos que se pueden prestar
     * @return Una lista que contiene los equipos que se pueden prestar
     * @throws ExcepcionServicios si existe un error cargando
     */
    public abstract List<EquipoComplejo> consultarDisponibles()throws ExcepcionServicios;

    /**
     * Actualiza un equipo
     * @param toUpdate el equipo actualizado que se guardara de nuevo
     * @throws ExcepcionServicios Si el equipo no esta registrado
     */
    public abstract void actualizarEquipo(EquipoComplejo toUpdate)throws ExcepcionServicios;

    /**
     * Consulta un modelo basado en el nombre
     * @param nombre del modelo
     * @return Modelo cargado
     * @throws ExcepcionServicios Si el modelo no esta registrado o Si la cadena esta vacia
     */
    public abstract Modelo consultarModelo(String nombre)throws ExcepcionServicios;
    
    /**
     * Consulta todos los modelos que contengan cierta cadena
     * @param cadena a buscar
     * @return todos los equipos que contengan la cadena
     * @throws ExcepcionServicios si la cadena está vacia
     */
    public abstract List<String> consultarAproximado(String cadena)throws ExcepcionServicios;
}
