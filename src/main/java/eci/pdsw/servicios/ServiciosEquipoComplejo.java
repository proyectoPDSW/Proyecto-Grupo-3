/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.servicios;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Modelo;
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

    public abstract void registrarModelo(Modelo model)throws ExcepcionServicios;

    public abstract void registrarEquipoComplejo(EquipoComplejo equipo)throws ExcepcionServicios;

    public abstract List<EquipoComplejo> consultarTodo()throws ExcepcionServicios;

    public abstract List<EquipoComplejo> consultarPorModelo(String modelo)throws ExcepcionServicios;

    public abstract EquipoComplejo consultarPorPlaca(int numPlaca)throws ExcepcionServicios;

    public abstract EquipoComplejo consultarPorSerial(String serial)throws ExcepcionServicios;

    public abstract List<EquipoComplejo> consultarDisponibles()throws ExcepcionServicios;

    public abstract void actualizarEquipo(EquipoComplejo toUpdate)throws ExcepcionServicios;

}
