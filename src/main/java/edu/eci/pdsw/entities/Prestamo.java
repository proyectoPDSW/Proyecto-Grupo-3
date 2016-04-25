/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo Alvarez
 */
public abstract class Prestamo implements Comparable<Prestamo> {
    protected int idPrestamo;
    protected Timestamp fechaInicio;
    protected Timestamp fechaEstimadaDeEntrega;
    protected Timestamp fechaRealEntregada;
    protected List<EquipoComplejo> equiposComplejosPrestados;
    protected List<EquipoComplejo> equiposComplejosFaltantes;
    protected Map<EquipoSencillo,Integer> equiposSencillosPrestados;
    protected Map<EquipoSencillo,Integer> equiposSencillosFaltantes;
    protected List<EquipoSencillo> sencillosFaltantes;
    protected Persona elQuePideElPrestamo;
    protected String tipo_prestamo;

    
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
        Collections.sort(equiposComplejosFaltantes);
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
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return the fechaEstimadaDeEntrega
     */
    public Timestamp getFechaEstimadaDeEntrega() {
        return fechaEstimadaDeEntrega;
    }

    /**
     * @return the fechaRealEntregada
     */
    public Timestamp getFechaRealEntregada() {
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
    public List<EquipoSencillo> getSencillosFaltantes() {
        return sencillosFaltantes;
    }

    public void setSencillosFaltantes(List<EquipoSencillo> sencillosFaltantes) {
        this.sencillosFaltantes = sencillosFaltantes;
    }

    public String getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(String tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }
    
    @Override
    public abstract int compareTo(Prestamo o);

}
