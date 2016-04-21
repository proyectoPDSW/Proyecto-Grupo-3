/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo Alvarez
 */
public abstract class Prestamo {
    protected int idPrestamo;
    protected Date fechaInicio;
    protected Date fechaEstimadaDeEntrega;
    protected Date fechaRealEntregada;
    protected List<EquipoComplejo> equiposComplejosPrestados;
    protected List<EquipoComplejo> equiposComplejosFaltantes;
    protected Map<EquipoSencillo,Integer> equiposSencillosPrestados;
    protected Map<EquipoSencillo,Integer> equiposSencillosFaltantes;
    protected Persona elQuePideElPrestamo;
    
    @Override
    public abstract String toString();
    /**
     * Obj: Saber si el prestamo esta en mora o no.
     * Pre: Ninguna.
     * Pos: devolver True si el prestamo esta en mora o no.
     * @return True, si el prestamo esta en mora, False d.l.c
     */
    public abstract boolean enMora();

    /**
     * @return the equiposComplejosFaltantes
     */
    public List<EquipoComplejo> getEquiposComplejosFaltantes() {
        return equiposComplejosFaltantes;
    }

    /**
     * @return the equiposSencillosFaltantes
     */
    public Map<EquipoSencillo,Integer> getEquiposSencillosFaltantes() {
        return equiposSencillosFaltantes;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return the fechaEstimadaDeEntrega
     */
    public Date getFechaEstimadaDeEntrega() {
        return fechaEstimadaDeEntrega;
    }

    /**
     * @return the fechaRealEntregada
     */
    public Date getFechaRealEntregada() {
        return fechaRealEntregada;
    }

    /**
     * @return the equiposComplejosPrestados
     */
    public List<EquipoComplejo> getEquiposComplejosPrestados() {
        return equiposComplejosPrestados;
    }

    /**
     * @param equiposComplejosFaltantes the equiposComplejosFaltantes to set
     */
    public void setEquiposComplejosFaltantes(List<EquipoComplejo> equiposComplejosFaltantes) {
        this.equiposComplejosFaltantes = equiposComplejosFaltantes;
    }

    /**
     * @return the equiposSencillosPrestados
     */
    public Map<EquipoSencillo,Integer> getEquiposSencillosPrestados() {
        return equiposSencillosPrestados;
    }

    /**
     * @return the elQuePideElPrestamo
     */
    public Persona getElQuePideElPrestamo() {
        return elQuePideElPrestamo;
    }
}
