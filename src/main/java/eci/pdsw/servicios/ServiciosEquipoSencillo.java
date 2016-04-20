/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.servicios;

import eci.pdsw.entities.EquipoSencillo;
import java.util.List;

/**
 *
 * @author 2107803
 */
public abstract class ServiciosEquipoSencillo {

    private static ServiciosEquipoSencillo instance = new ServiciosEquipoSencilloPersistence();

    public static ServiciosEquipoSencillo getInstance() {
        return instance;
    }

    public abstract void registrarEquipoSencillo(EquipoSencillo equipo) throws ExcepcionServicios;

    public abstract EquipoSencillo consultarPorNombre(String nombre) throws ExcepcionServicios;

    public abstract List<EquipoSencillo> consultarTodo() throws ExcepcionServicios;

    public abstract int ConsultarDisponibilidadPorNombre(String nombre) throws ExcepcionServicios;

    public abstract void actualizar(EquipoSencillo equipo) throws ExcepcionServicios;
}
