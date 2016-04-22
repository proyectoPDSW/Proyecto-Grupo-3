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
    private int idPrestamo;
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

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaEstimadaDeEntrega(Date fechaEstimadaDeEntrega) {
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
    }

    public void setFechaRealEntregada(Date fechaRealEntregada) {
        this.fechaRealEntregada = fechaRealEntregada;
    }

    public void setEquiposComplejosPrestados(List<EquipoComplejo> equiposComplejosPrestados) {
        this.equiposComplejosPrestados = equiposComplejosPrestados;
    }

    public void setEquiposSencillosPrestados(Map<EquipoSencillo, Integer> equiposSencillosPrestados) {
        this.equiposSencillosPrestados = equiposSencillosPrestados;
    }

    public void setEquiposSencillosFaltantes(Map<EquipoSencillo, Integer> equiposSencillosFaltantes) {
        this.equiposSencillosFaltantes = equiposSencillosFaltantes;
    }

    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }
    
}
